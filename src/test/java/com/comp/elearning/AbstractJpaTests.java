/**
 *
 */
package com.comp.elearning;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:servlet-context.xml" })
@TransactionConfiguration
@Transactional
public abstract class AbstractJpaTests {

}
