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

package local.example.zen.probe

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.ojalgo.optimisation.ExpressionsBasedModel
import org.ojalgo.optimisation.Optimisation.State.OPTIMAL
import org.ojalgo.optimisation.Variable
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class LocalizationStrategyOfHealthServicesProblemTests {

    @Test
    fun minimizationTest() {
        val sanitaryDistricts = arrayOf(
                Variable("district1").lower(0).weight(1).integer(true).relax(),
                Variable("district2").lower(0).weight(1).integer(true).relax(),
                Variable("district3").lower(0).weight(1).integer(true).relax(),
                Variable("district4").lower(0).weight(1).integer(true).relax(),
                Variable("district5").lower(0).weight(1).integer(true).relax(),
                Variable("district6").lower(0).weight(1).integer(true).relax())
        val model = ExpressionsBasedModel()
        model.addVariables(sanitaryDistricts)
        val needsOfTheDistrict1 = model.addExpression().lower(1)
        val needsOfTheDistrict2 = model.addExpression().lower(1)
        val needsOfTheDistrict3 = model.addExpression().lower(1)
        val needsOfTheDistrict4 = model.addExpression().lower(1)
        val needsOfTheDistrict5 = model.addExpression().lower(1)
        val needsOfTheDistrict6 = model.addExpression().lower(1)
        needsOfTheDistrict1.set(0, 1).set(1, 1)
        needsOfTheDistrict2.set(0, 1).set(1, 1).set(5, 1)
        needsOfTheDistrict3.set(2, 1).set(3, 1)
        needsOfTheDistrict4.set(0, 1).set(1, 1).set(4, 1)
        needsOfTheDistrict5.set(3, 1).set(4, 1).set(5, 1)
        needsOfTheDistrict6.set(1, 1).set(4, 1).set(5, 1)
        val result = model.minimise()
        assertEquals("", result.state, OPTIMAL)
        assertEquals(2, java.lang.Double.valueOf(result.value).toLong())
        assertEquals(0, java.lang.Double.valueOf(result.doubleValue(0)).toLong())
        assertEquals(1, java.lang.Double.valueOf(result.doubleValue(1)).toLong())
        assertEquals(0, java.lang.Double.valueOf(result.doubleValue(2)).toLong())
        assertEquals(1, java.lang.Double.valueOf(result.doubleValue(3)).toLong())
        assertEquals(0, java.lang.Double.valueOf(result.doubleValue(4)).toLong())
        assertEquals(0, java.lang.Double.valueOf(result.doubleValue(5)).toLong())
    }
}
