/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package br.gov.mg.fazenda.recaptcha.component.recaptcha;

import java.io.StringWriter;

import org.apache.myfaces.test.base.AbstractJsfTestCase;
import org.apache.myfaces.test.mock.MockRenderKitFactory;
import org.apache.myfaces.test.mock.MockResponseWriter;

import br.gov.mg.fazenda.recaptcha.component.recaptcha.RecaptchaRenderer;

/**
 * @author leonardo luz
 * @version 0.1
 * @since 23/06/2015
 * 
 * Class to test the behaviour of the recatpcha renderer
 */
public class RecaptchaRendererTest extends AbstractJsfTestCase
{

    private MockResponseWriter writer ;
    private Recaptcha recaptcha;

    public RecaptchaRendererTest(String name)
    {
        super(name);
    }

    public void setUp() throws Exception
    {
        super.setUp();

        recaptcha = new Recaptcha();
        recaptcha.setDataPublicKey("TODO");
        recaptcha.setDataPrivateKey("TODO");

        writer = new MockResponseWriter(new StringWriter(), null, null);
        facesContext.setResponseWriter(writer);
        // TODO remove these two lines once shale-test goes alpha, see MYFACES-1155
        facesContext.getViewRoot().setRenderKitId(MockRenderKitFactory.HTML_BASIC_RENDER_KIT);
        facesContext.getRenderKit().addRenderer(
                recaptcha.getFamily(),
                recaptcha.getRendererType(),
                new RecaptchaRenderer());
    }

    public void tearDown() throws Exception
    {
        super.tearDown();
        recaptcha = null;
        writer = null;
    }

    public void testEncodeEnd()  throws Exception
    {
        recaptcha.encodeEnd(facesContext);
        facesContext.renderResponse();

        String output = writer.getWriter().toString();

        assertEquals("<div", output);
        assertNotSame("<div", output);
    }

}
