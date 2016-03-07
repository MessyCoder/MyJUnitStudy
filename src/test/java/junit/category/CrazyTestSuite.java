package junit.category;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Categories.class)

/**
 * 运行那些声明了@Category({CrazyTests.class})的测试方法。
 * 没有被@Category注释的测试方法不会被运行。
 */
@Categories.IncludeCategory(CrazyTests.class)

/**
 * 不运行那些声明了@Category({SmartTests.class})的测试方法。
 *
 */
@Categories.ExcludeCategory(SmartTests.class)
@Suite.SuiteClasses({SomeTest.class, OtherTest.class})
public class CrazyTestSuite {
}
