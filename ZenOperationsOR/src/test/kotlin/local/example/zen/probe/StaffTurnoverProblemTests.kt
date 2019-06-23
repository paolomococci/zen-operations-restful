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
import org.ojalgo.optimisation.Optimisation.State
import org.ojalgo.optimisation.Variable
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class StaffTurnoverProblemTests {

    @Test
    fun minimizationOfMorningStaffTest() {
        val weak = arrayOf(
                Variable("beginsMonday").lower(0).weight(1).integer(true).relax(),
                Variable("beginsTuesday").lower(0).weight(1).integer(true).relax(),
                Variable("beginsWednesday").lower(0).weight(1).integer(true).relax(),
                Variable("beginsThursday").lower(0).weight(1).integer(true).relax(),
                Variable("beginsFriday").lower(0).weight(1).integer(true).relax(),
                Variable("beginsSaturday").lower(0).weight(1).integer(true).relax(),
                Variable("beginsSunday").lower(0).weight(1).integer(true).relax())
        val model = ExpressionsBasedModel()
        val mon = model.addExpression().lower(17)
        val tue = model.addExpression().lower(13)
        val wed = model.addExpression().lower(15)
        val thu = model.addExpression().lower(19)
        val fri = model.addExpression().lower(14)
        val sat = model.addExpression().lower(16)
        val sun = model.addExpression().lower(11)
        model.addVariables(weak)
        mon.set(0, 1).set(3, 1).set(4, 1).set(5, 1).set(6, 1)
        tue.set(0, 1).set(1, 1).set(4, 1).set(5, 1).set(6, 1)
        wed.set(0, 1).set(1, 1).set(2, 1).set(5, 1).set(6, 1)
        thu.set(0, 1).set(1, 1).set(2, 1).set(3, 1).set(6, 1)
        fri.set(0, 1).set(1, 1).set(2, 1).set(3, 1).set(4, 1)
        sat.set(1, 1).set(2, 1).set(3, 1).set(4, 1).set(5, 1)
        sun.set(2, 1).set(3, 1).set(4, 1).set(5, 1).set(6, 1)
        val result = model.minimise()
        assertEquals("", result.state, State.OPTIMAL)
        assertEquals(22, java.lang.Double.valueOf(result.value).toLong())
        assertEquals(1, java.lang.Double.valueOf(result.doubleValue(0)).toLong())
        assertEquals(3, java.lang.Double.valueOf(result.doubleValue(1)).toLong())
        assertEquals(2, java.lang.Double.valueOf(result.doubleValue(2)).toLong())
        assertEquals(7, java.lang.Double.valueOf(result.doubleValue(3)).toLong())
        assertEquals(0, java.lang.Double.valueOf(result.doubleValue(4)).toLong())
        assertEquals(3, java.lang.Double.valueOf(result.doubleValue(5)).toLong())
        assertEquals(5, java.lang.Double.valueOf(result.doubleValue(6)).toLong())
    }
}
