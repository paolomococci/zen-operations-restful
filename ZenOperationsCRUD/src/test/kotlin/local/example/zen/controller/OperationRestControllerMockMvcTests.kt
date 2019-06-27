/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.zen.controller

import org.hamcrest.Matchers.containsString
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class OperationRestControllerMockMvcTests {

    @Autowired
    private val mockMvc: MockMvc? = null

    private val sample: String = "{\"name\":\"sample operation\"}"

    @Test
    @Throws(Exception::class)
    fun `existence test`() {
        mockMvc!!.perform(get("/api/operations"))
                .andDo(print()).andExpect(status().isOk)
                .andExpect(jsonPath("$._links.self.href")
                        .value("http://localhost/api/operations"))
    }


    @Test
    @Throws(Exception::class)
    fun `create test`() {
        mockMvc!!.perform(post("/api/operations")
                .content(sample).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated)
                .andExpect(header().string("Location", containsString("api/operations/")))
    }

    @Test
    @Throws(Exception::class)
    fun `retrieve test`() {
        val mvcResult = mockMvc!!.perform(post("/api/operations")
                .content(sample).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated).andReturn()
        val result = mvcResult.response.getHeader("Location")
        mockMvc.perform(get(result!!)).andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value("sample operation"))
    }
}
