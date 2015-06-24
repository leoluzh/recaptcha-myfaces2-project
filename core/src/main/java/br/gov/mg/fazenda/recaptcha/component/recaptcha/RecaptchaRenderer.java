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

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

import br.gov.mg.fazenda.recaptcha.ReCaptcha;
import br.gov.mg.fazenda.recaptcha.ReCaptchaFactory;
import br.gov.mg.fazenda.recaptcha.internals.ReCaptchaJsApi;

/**
 * @author leonardo luz
 * @version 0.1
 * @since 23/06/2015
 * 
 */
@JSFRenderer(
   renderKitId = "HTML_BASIC",
   family = "javax.faces.Output",
   type = "br.gov.mg.fazenda.recaptcha.RecaptchaRenderer")
public class RecaptchaRenderer extends Renderer
{

    public void decode(FacesContext facesContext, UIComponent uiComponent)
    {
        super.decode(facesContext, uiComponent);
        // nothing to decode
    }

    public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException
    {
        super.encodeBegin(facesContext, uiComponent);
        // no need to use encodeBegin.
    }

    public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException
    {
        super.encodeChildren(facesContext, uiComponent);
        // this component does not have children
    }

    public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException
    {
        super.encodeEnd(facesContext, uiComponent);

        if (!uiComponent.isRendered())
        {
            return;
        }

        ResponseWriter writer = facesContext.getResponseWriter();
        Recaptcha recaptcha = (Recaptcha) uiComponent;
        
        String id = recaptcha.getId();
        String siteKey = recaptcha.getDataPublicKey();
        String secretKey = recaptcha.getDataPrivateKey();
        boolean includeNoScript = false ;
        
        //if( recaptcha.isDataIncludeNoScript() != null ){
        includeNoScript = recaptcha.isDataIncludeNoScript();
        //}
        
        ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptchaSecure(siteKey, secretKey,includeNoScript);

        //TODO: TRY TO INCLUDE OTHER NEW TAG PARAMETERS ... REFACTORY API (implemented beta)
        String html = reCaptcha.createRecaptchaHtml(id,
        		recaptcha.getDataTheme(),
        		recaptcha.getDataType(),
        		recaptcha.getDataCallback(),
        		recaptcha.getDataClass(),
        		recaptcha.getDataStyle() );

        //TODO: HOW TO PUT THIS CODE IN HEADER SECTION??
        String js =
        		ReCaptchaJsApi.script(
        				recaptcha.getDataOnLoad(),
        				recaptcha.getDataRender(),
        				recaptcha.getDataLanguage() ,
        				true );

        writer.write(html);
        writer.write(js);
        
        
    }
}
