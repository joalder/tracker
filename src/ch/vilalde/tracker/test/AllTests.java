package ch.vilalde.tracker.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for running all (enabled) tests
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDataPersistence.class
})
public class AllTests {

}
