/*
 *     Copyright 2017 IBM Corp.
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package com.ibm.mobilefirstplatform.serversdk.java.push.builders;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mobilefirstplatform.serversdk.java.push.internal.PushMessageModel.Settings.ChromeWeb;

public final class ChromeWebBuilder {

	public static final Logger logger = Logger.getLogger(ChromeAppExtBuilder.class.getName());

	private String title;
	private String iconUrl;
	private Integer timeToLive;
	private JsonNode payload;

	public final ChromeWebBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public final ChromeWebBuilder setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
		return this;
	}

	public final ChromeWebBuilder setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
		return this;
	}

	public final ChromeWebBuilder setPayload(JSONObject payload) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNodePayload = null;

		try {
			if (payload != null) {
				jsonNodePayload = mapper.readTree(payload.toString());
			}
		} catch (Exception exception) {
			logger.log(Level.SEVERE, exception.toString(), exception);
		}

		this.payload = jsonNodePayload;
		return this;
	}

	public ChromeWeb build() {
		ChromeWeb chromeWeb = new ChromeWeb();
		chromeWeb.setIconUrl(iconUrl).setPayload(payload).setTimeToLive(timeToLive).setTitle(title);
		return chromeWeb;

	}
}