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
class RefineryProblemTests {

    @Test
    fun maximizationWithoutAdvertisingTest() {
        /**
            Xij: matrix of decision variables,
            associates the quantities of barrels of crude oils purchased
            to the quantities of diesel fuel barrels produced
         */
        val objectiveFunction = arrayOf(
                with(Variable("x11")) {
                    lower(0).weight(21)
                },
                with(Variable("x12")) {
                    lower(0).weight(11)
                },
                with(Variable("x13")) {
                    lower(0).weight(1)
                },
                with(Variable("x21")) {
                    lower(0).weight(31)
                },
                with(Variable("x22")) {
                    lower(0).weight(21)
                },
                with(Variable("x23")) {
                    lower(0).weight(11)
                },
                with(Variable("x31")) {
                    lower(0).weight(41)
                },
                with(Variable("x32")) {
                    lower(0).weight(31)
                },
                with(Variable("x33")) {
                    lower(0).weight(21)
                })
        val model = ExpressionsBasedModel()
        model.addVariables(objectiveFunction)
        val applicationConstraintOfDieselFuelTypeOne = model.addExpression().level(3000)
        val applicationConstraintOfDieselFuelTypeTwo = model.addExpression().level(2000)
        val applicationConstraintOfDieselFuelTypeThree = model.addExpression().level(1000)
        val applicationConstraintOfCrudeOilTypeOne = model.addExpression().upper(5000)
        val applicationConstraintOfCrudeOilTypeTwo = model.addExpression().upper(5000)
        val applicationConstraintOfCrudeOilTypeThree = model.addExpression().upper(5000)
        val applicationConstraintProductionOfTheRefinery = model.addExpression().upper(14000)
        val applicationConstraintOfQualityOnTheMinimumNumberOfOctane1 = model.addExpression().lower(0)
        val applicationConstraintOfQualityOnTheMinimumNumberOfOctane2 = model.addExpression().lower(0)
        val applicationConstraintOfQualityOnTheMinimumNumberOfOctane3 = model.addExpression().lower(0)
        val applicationConstraintOfMaximumSulfurContentInCrudeOilType1 = model.addExpression().upper(0)
        val applicationConstraintOfMaximumSulfurContentInCrudeOilType2 = model.addExpression().upper(0)
        val applicationConstraintOfMaximumSulfurContentInCrudeOilType3 = model.addExpression().upper(0)
        applicationConstraintOfDieselFuelTypeOne.set(0, 1).set(1, 1).set(2, 1)
        applicationConstraintOfDieselFuelTypeTwo.set(3, 1).set(4, 1).set(5, 1)
        applicationConstraintOfDieselFuelTypeThree.set(6, 1).set(7, 1).set(8, 1)
        applicationConstraintOfCrudeOilTypeOne.set(0, 1).set(3, 1).set(6, 1)
        applicationConstraintOfCrudeOilTypeTwo.set(1, 1).set(4, 1).set(7, 1)
        applicationConstraintOfCrudeOilTypeThree.set(2, 1).set(5, 1).set(8, 1)
        applicationConstraintProductionOfTheRefinery
                .set(0, 1).set(1, 1).set(2, 1).set(3, 1).set(4, 1).set(5, 1).set(6, 1).set(7, 1).set(8, 1)
        applicationConstraintOfQualityOnTheMinimumNumberOfOctane1.set(0, 2).set(1, -4).set(2, -2)
        applicationConstraintOfQualityOnTheMinimumNumberOfOctane2.set(3, 4).set(4, -2)
        applicationConstraintOfQualityOnTheMinimumNumberOfOctane3.set(6, 6).set(8, 2)
        applicationConstraintOfMaximumSulfurContentInCrudeOilType1.set(0, -0.005).set(1, 0.01).set(2, 0.02)
        applicationConstraintOfMaximumSulfurContentInCrudeOilType2.set(3, -0.015).set(5, 0.01)
        applicationConstraintOfMaximumSulfurContentInCrudeOilType3.set(6, -0.005).set(7, 0.01).set(8, 0.02)
        val result = model.maximise()
        assertEquals("", result.state, OPTIMAL)
        assertEquals(156000.0, result.value, 0.01)
        assertEquals(3000.0, result.doubleValue(0), 0.01)
        assertEquals(0.0, result.doubleValue(1), 0.01)
        assertEquals(0.0, result.doubleValue(2), 0.01)
        assertEquals(1000.0, result.doubleValue(3), 0.01)
        assertEquals(1000.0, result.doubleValue(4), 0.01)
        assertEquals(0.0, result.doubleValue(5), 0.01)
        assertEquals(1000.0, result.doubleValue(6), 0.01)
        assertEquals(0.0, result.doubleValue(7), 0.01)
        assertEquals(0.0, result.doubleValue(8), 0.01)
    }

    @Test
    fun maximizationWithAdvertisingTest() {
        /**
            Xij: matrix of decision variables,
            associates the quantities of barrels of crude oils purchased
            to the quantities of diesel fuel barrels produced.
            a1, a2, a3: to advertise diesel fuel one two and three, respectively.
         */
        val objectiveFunction = arrayOf(
                Variable("x11").run {
                    lower(0).weight(21)
                },
                Variable("x12").run {
                    lower(0).weight(11)
                },
                Variable("x13").run {
                    lower(0).weight(1)
                },
                Variable("x21").run {
                    lower(0).weight(31)
                },
                Variable("x22").run {
                    lower(0).weight(21)
                },
                Variable("x23").run {
                    lower(0).weight(11)
                },
                Variable("x31").run {
                    lower(0).weight(41)
                },
                Variable("x32").run {
                    lower(0).weight(31)
                },
                Variable("x33").run {
                    lower(0).weight(21)
                },
                Variable("a1").run {
                    lower(0).weight(-1)
                },
                Variable("a2").run {
                    lower(0).weight(-1)
                },
                Variable("a3").run {
                    lower(0).weight(-1)
                })
        val model = ExpressionsBasedModel()
        model.addVariables(objectiveFunction)
        val applicationConstraintOfDieselFuelTypeOne = model.addExpression().level(3000)
        val applicationConstraintOfDieselFuelTypeTwo = model.addExpression().level(2000)
        val applicationConstraintOfDieselFuelTypeThree = model.addExpression().level(1000)
        val applicationConstraintOfCrudeOilTypeOne = model.addExpression().upper(5000)
        val applicationConstraintOfCrudeOilTypeTwo = model.addExpression().upper(5000)
        val applicationConstraintOfCrudeOilTypeThree = model.addExpression().upper(5000)
        val applicationConstraintProductionOfTheRefinery = model.addExpression().upper(14000)
        val applicationConstraintOfQualityOnTheMinimumNumberOfOctane1 = model.addExpression().lower(0)
        val applicationConstraintOfQualityOnTheMinimumNumberOfOctane2 = model.addExpression().lower(0)
        val applicationConstraintOfQualityOnTheMinimumNumberOfOctane3 = model.addExpression().lower(0)
        val applicationConstraintOfMaximumSulfurContentInCrudeOilType1 = model.addExpression().upper(0)
        val applicationConstraintOfMaximumSulfurContentInCrudeOilType2 = model.addExpression().upper(0)
        val applicationConstraintOfMaximumSulfurContentInCrudeOilType3 = model.addExpression().upper(0)
        applicationConstraintOfDieselFuelTypeOne.set(0, 1).set(1, 1).set(2, 1).set(9, -10)
        applicationConstraintOfDieselFuelTypeTwo.set(3, 1).set(4, 1).set(5, 1).set(10, -10)
        applicationConstraintOfDieselFuelTypeThree.set(6, 1).set(7, 1).set(8, 1).set(11, -10)
        applicationConstraintOfCrudeOilTypeOne.set(0, 1).set(3, 1).set(6, 1)
        applicationConstraintOfCrudeOilTypeTwo.set(1, 1).set(4, 1).set(7, 1)
        applicationConstraintOfCrudeOilTypeThree.set(2, 1).set(5, 1).set(8, 1)
        applicationConstraintProductionOfTheRefinery
                .set(0, 1).set(1, 1).set(2, 1).set(3, 1).set(4, 1).set(5, 1).set(6, 1).set(7, 1).set(8, 1)
        applicationConstraintOfQualityOnTheMinimumNumberOfOctane1.set(0, 2).set(1, -4).set(2, -2)
        applicationConstraintOfQualityOnTheMinimumNumberOfOctane2.set(3, 4).set(4, -2)
        applicationConstraintOfQualityOnTheMinimumNumberOfOctane3.set(6, 6).set(8, 2)
        applicationConstraintOfMaximumSulfurContentInCrudeOilType1.set(0, -0.005).set(1, 0.01).set(2, 0.02)
        applicationConstraintOfMaximumSulfurContentInCrudeOilType2.set(3, -0.015).set(5, 0.01)
        applicationConstraintOfMaximumSulfurContentInCrudeOilType3.set(6, -0.005).set(7, 0.01).set(8, 0.02)
        val result = model.maximise()
        assertEquals("", result.state, OPTIMAL)
        assertEquals(277750.0, result.value, 0.0)
        assertEquals(2000.0, result.doubleValue(0), 0.0)
        assertEquals(1000.0, result.doubleValue(1), 0.0)
        assertEquals(0.0, result.doubleValue(2), 0.0)
        assertEquals(2200.0, result.doubleValue(3), 0.0)
        assertEquals(4000.0, result.doubleValue(4), 0.01)
        assertEquals(3300.0, result.doubleValue(5), 0.0)
        assertEquals(800.0, result.doubleValue(6), 0.0)
        assertEquals(0.0, result.doubleValue(7), 0.0)
        assertEquals(200.0, result.doubleValue(8), 0.0)
        assertEquals(0.0, result.doubleValue(9), 0.0)
        assertEquals(750.0, result.doubleValue(10), 0.0)
        assertEquals(0.0, result.doubleValue(11), 0.0)
    }
}
