/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.binu.starterjpa;

import com.binu.starterjpa.repo.TutorialRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TutorialTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TutorialRepo tutorialRepo;

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		tutorialRepo.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links.extracts").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/extract").content(
				"{\"title\": \"Tutorial 1\", \"description\": \"Tutorial One\", \"published\": \"true\"}")).andExpect(
						status().isCreated()).andExpect(
								header().string("Location", containsString("extract/")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/extract").content(
				"{\"title\": \"Tutorial 1\", \"description\": \"Tutorial One\", \"published\": \"true\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.title").value("Tutorial 1")).andExpect(
						jsonPath("$.description").value("Tutorial One"));
	}


	/*
	@Test
	public void shouldQueryEntity() throws Exception {

		mockMvc.perform(post("/extract").content(
				"{\"name\": \"Extract 1\", \"description\": \"Extract One\", \"contactEmail\": \"abc@example.org\"}")).andExpect(
						status().isCreated());

		mockMvc.perform(
				get("/extract/search/findByName?name={name}", "Extract One")).andExpect(
						status().isOk()).andExpect(
								jsonPath("$.contactEmail").value(
										"abc@example.org"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/customer").content(
				"{\"taxId\": \"1000101\", \"name\":\"PNC Bank\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"taxId\": \"1000101\", \"name\":\"PNCAssociates\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.name").value("PNCAssociates")).andExpect(
						jsonPath("$.taxId").value("1000101"));
	}
	*/


	@Test
	public void shouldDeleteEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/extract").content(
				"{\"title\": \"Tutorial 1\", \"description\": \"Tutorial One\", \"published\": \"true\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
