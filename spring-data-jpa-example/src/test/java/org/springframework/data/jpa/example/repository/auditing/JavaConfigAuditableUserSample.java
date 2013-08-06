/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.jpa.example.repository.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.jpa.domain.support.AuditingBeanFactoryPostProcessor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.example.auditing.AuditableUser;
import org.springframework.data.jpa.example.auditing.AuditorAwareImpl;
import org.springframework.data.jpa.example.repository.InfrastructureConfig;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Thomas Darimont
 */
@ContextConfiguration
public class JavaConfigAuditableUserSample extends AbstractAuditableUserSample {

	@Configuration
	@Import(InfrastructureConfig.class)
	@EnableJpaRepositories
	static class Config {

		@Bean
		@Scope("prototype")
		public AuditingEntityListener<AuditableUser> auditingEntityListener() {
			AuditingEntityListener<AuditableUser> auditingEntityListener = new AuditingEntityListener<AuditableUser>();
			AuditingHandler<AuditableUser> auditingHandler = new AuditingHandler<AuditableUser>();
			auditingHandler.setAuditorAware(new AuditorAwareImpl());
			auditingEntityListener.setAuditingHandler(auditingHandler);
			return auditingEntityListener;
		}

		@Bean
		public AuditingBeanFactoryPostProcessor auditing() {
			return new AuditingBeanFactoryPostProcessor();
		}
	}

}