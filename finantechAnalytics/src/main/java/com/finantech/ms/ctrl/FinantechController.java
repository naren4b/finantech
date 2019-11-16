/*
 * Copyright 2013-2015 MongoDB Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.finantech.ms.ctrl;

import static com.finantech.ms.collector.ScripInfo.getScripInfo;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.setPort;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import com.finantech.ms.collector.BufferedReaderExample;
import com.finantech.ms.collector.Morningstar;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.ScripInfo;
import model.ScripTradeInfoDetails;
import service.FinanTechService;
import spark.Request;
import spark.Response;
import spark.Route;

public class FinantechController {

    String data = null;

    private final Configuration cfg;

    FinanTechService finanTechService = new FinanTechService();

    public static void main(String[] args) throws IOException {
        new FinantechController("mongodb://localhost", args);

    }

    public FinantechController(String mongoURIString, Object data)
            throws IOException {

        cfg = createFreemarkerConfiguration();
        setPort(8082);
        initializeRoutes();
    }

    abstract class FreemarkerBasedRoute extends Route {

        final Template template;

        /**
         * Constructor
         *
         * @param path
         *            The route path which is used for matching. (e.g.
         *            /hello, users/:name)
         */
        protected FreemarkerBasedRoute(final String path,
                final String templateName) throws IOException {
            super(path);
            template = cfg.getTemplate(templateName);
        }

        @Override
        public Object handle(Request request, Response response) {
            StringWriter writer = new StringWriter();
            try {
                doHandle(request, response, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/internal_error");
            }
            return writer;
        }

        protected abstract void doHandle(final Request request,
                final Response response, final Writer writer)
                throws IOException, TemplateException;

    }

    private void initializeRoutes() throws IOException {

        // present the login page
        get(showWelcomePage());
        get(showMorningstarPage());
        post(showScreenEODPage());

    }

    private FreemarkerBasedRoute showScreenEODPage() throws IOException {
        return new FreemarkerBasedRoute("/ScreenEODPage", "scripInfo.ftl") {

            @Override
            protected void doHandle(Request request, Response response,
                    Writer writer) throws IOException, TemplateException {
                String params = request.queryParams("scripId");
                loadData();
                SimpleHash root = new SimpleHash();
                root.put("dataTable", data);
                root.put("scripName", params);
                root.put("date", util.FinanTechUtil
                        .getFormattedDateString(new Date(), "dd-MMM"));

                template.process(root, writer);
            }

            private void loadData() throws IOException {

                ScripTradeInfoDetails scripTradeInfoDetails = finanTechService
                        .getScripTradeInfoDetails("EICHERMOT");
                data = getScripInfo(scripTradeInfoDetails);

            }

        };
    }

    private FreemarkerBasedRoute showMorningstarPage() throws IOException {
        return new FreemarkerBasedRoute("/morningstar", "morningstar.ftl") {

            @Override
            protected void doHandle(Request request, Response response,
                    Writer writer) throws IOException, TemplateException {
                loadData();
                SimpleHash root = new SimpleHash();
                root.put("dataTable", data);
                template.process(root, writer);
            }

            private void loadData() throws IOException {
                File f = new File(
                        "D:\\Personal\\personal\\Hadoop\\project\\blog-final\\src\\main\\resources\\freemarker\\test.csv");
                if (f == null) {
                    data = Morningstar.collectData();
                } else {
                    long lastmodified = f.lastModified();
                    if ((System.currentTimeMillis() - lastmodified) > (5
                            * 1000 * 60 * 10)) {
                        data = Morningstar.collectData();
                    } else {
                        data = BufferedReaderExample.readFile();
                    }
                }
            }

        };
    }

    private FreemarkerBasedRoute showWelcomePage() throws IOException {
        return new FreemarkerBasedRoute("/", "welcome.ftl") {

            private StringBuffer options = new StringBuffer();

            @Override
            protected void doHandle(Request request, Response response,
                    Writer writer) throws IOException, TemplateException {

                SimpleHash root = new SimpleHash();

                List<ScripInfo> allScripDetails = finanTechService
                        .getAllScripDetails();
                for (ScripInfo scripInfo : allScripDetails) {
                    options.append("<option value=\""
                            + scripInfo.getScripName() + "\">"
                            + scripInfo.getScripName() + "</option>");
                }
                root.put("options", options.toString());
                template.process(root, writer);
            }
        };
    }

    private Configuration createFreemarkerConfiguration() {
        Configuration retVal = new Configuration();
        retVal.setClassForTemplateLoading(FinantechController.class,
                "/freemarker");
        return retVal;
    }
}
