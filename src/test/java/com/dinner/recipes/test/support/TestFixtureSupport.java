package com.dinner.recipes.test.support;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public abstract class TestFixtureSupport extends TestSupport {

    @BeforeClass
    public static void beforeTestClass() {
        FixtureFactoryLoader.loadTemplates("com.dinner.recipes.test.template.loader");
    }
}