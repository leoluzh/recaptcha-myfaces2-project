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

import javax.faces.component.UIOutput;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

/**
 * @author leonardo luz
 * @version 0.1
 * @since 23/06/2015   
 *   
 */
@JSFComponent(
        name = "mycomponents:recaptcha",
        clazz="br.gov.mg.fazenda.recaptcha.component.recaptcha.Recaptcha",
        tagClass = "br.gov.mg.fazenda.recaptcha.component.recaptcha.RecaptchaTag")
public abstract class AbstractRecaptcha extends UIOutput
{
    public static final String COMPONENT_TYPE = "br.gov.mg.fazenda.recaptcha.Recaptcha";
    public static final String DEFAULT_RENDERER_TYPE = "br.gov.mg.fazenda.recaptcha.RecaptchaRenderer";
    public static final String COMPONENT_FAMILY = "javax.faces.Output";

    @JSFProperty 
    public abstract String getDataPublicKey();
    
    @JSFProperty 
    public abstract String getDataPrivateKey();
    
    //js parameters
    @JSFProperty 
    public abstract String getDataOnLoad();
    
    @JSFProperty 
    public abstract String getDataRender();
    
    @JSFProperty 
    public abstract String getDataLanguage();
    
    //recaptcha tag parameters
    @JSFProperty 
    public abstract String getDataTheme();
    
    @JSFProperty 
    public abstract String getDataType();
    
    @JSFProperty 
    public abstract String getDataSize();
    
    @JSFProperty 
    public abstract String getDataTabIndex();
    
    @JSFProperty 
    public abstract String getDataCallback();
    
    @JSFProperty 
    public abstract String getDataExpiredCallback();
    
    @JSFProperty 
    public abstract String getDataClass();
    
    @JSFProperty 
    public abstract String getDataStyle();
    
    @JSFProperty(defaultValue="true") 
    public abstract boolean isDataIncludeNoScript();
    
    
}
