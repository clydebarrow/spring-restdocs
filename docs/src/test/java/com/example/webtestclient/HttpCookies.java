/*
 * Copyright 2014-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.webtestclient;

import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.cookies.CookieDocumentation.cookieWithName;
import static org.springframework.restdocs.cookies.CookieDocumentation.requestCookies;
import static org.springframework.restdocs.cookies.CookieDocumentation.responseCookies;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

public class HttpCookies {

	// @formatter:off

	private WebTestClient webTestClient;

	public void cookies() throws Exception {
		// tag::cookies[]
		this.webTestClient
			.get().uri("/people").cookie("JSESSIONID", "ACBCDFD0FF93D5BB=") // <1>
			.exchange().expectStatus().isOk().expectBody()
			.consumeWith(document("cookies",
				requestCookies( // <2>
					cookieWithName("JSESSIONID").description("Session token")), // <3>
				responseCookies( // <4>
					cookieWithName("JSESSIONID")
						.description("Updated session token"),
					cookieWithName("logged_in")
						.description("User is logged in"))));
		// end::cookies[]
	}
}
