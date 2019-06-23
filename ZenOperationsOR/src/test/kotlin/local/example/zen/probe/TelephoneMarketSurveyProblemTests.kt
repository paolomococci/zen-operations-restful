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

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.ojalgo.optimisation.ExpressionsBasedModel
import org.ojalgo.optimisation.Optimisation
import org.ojalgo.optimisation.Variable
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class TelephoneMarketSurveyProblemTests {

    @Test
    fun minimizationTest() {
        val morningCalls = Variable
                .make("morning").lower(0).weight(1.1).integer(true).relax()
        val eveningCalls = Variable
                .make("evening").lower(0).weight(1.6).integer(true).relax()
        val model = ExpressionsBasedModel()
        val marriedWoman = model.addExpression().lower(150)
        val unmarriedWoman = model.addExpression().lower(110)
        val marriedMan = model.addExpression().lower(120)
        val unmarriedMan = model.addExpression().lower(100)
        model.addVariable(morningCalls)
        model.addVariable(eveningCalls)
        marriedWoman.set(morningCalls, 0.3).set(eveningCalls, 0.3)
        unmarriedWoman.set(morningCalls, 0.1).set(eveningCalls, 0.2)
        marriedMan.set(morningCalls, 0.1).set(eveningCalls, 0.3)
        unmarriedMan.set(morningCalls, 0.1).set(eveningCalls, 0.15)
        val optimisation = model.minimise()
        Assert.assertEquals("", optimisation.state, Optimisation.State.OPTIMAL)
        Assert.assertEquals(1065.6, optimisation.value, 1.17)
        Assert.assertEquals(0, java.lang.Double.valueOf(optimisation.doubleValue(0)).toLong())
        Assert.assertEquals(666, java.lang.Double.valueOf(optimisation.doubleValue(1)).toLong())
    }
}
