package com.stewemetal.takehometemplate.shell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ExampleKotestUnitTest : BehaviorSpec({
    Given("a unit test with kotest") {
        When("the test is run") {
            Then("the test passes") {
                1 + 2 shouldBe 3
            }
        }
    }
})
