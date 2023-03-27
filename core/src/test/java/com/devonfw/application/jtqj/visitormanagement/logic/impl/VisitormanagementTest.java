package com.devonfw.application.jtqj.visitormanagement.logic.impl;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import com.devonfw.application.jtqj.SpringBootApp;
import com.devonfw.application.jtqj.visitormanagement.logic.api.Visitormanagement;
import com.devonfw.application.jtqj.visitormanagement.logic.api.to.VisitorEto;
import com.devonfw.application.jtqj.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;
import com.devonfw.module.test.common.base.ComponentTest;

@SpringBootTest(classes = SpringBootApp.class)
public class VisitormanagementTest extends ComponentTest {

  @Inject
  private Visitormanagement visitormanagement;

  @Test
  public void saveVisitorTest() {

    VisitorEto visitorEto = new VisitorEto();
    visitorEto.setName("Mary");
    visitorEto.setUsername("mary@mary.com");
    visitorEto.setPhoneNumber("123456789");
    visitorEto.setPassword("test");
    visitorEto.setUserType(false);
    visitorEto.setAcceptedTerms(true);
    visitorEto.setAcceptedCommercial(true);
    VisitorEto visitorEtoResult = this.visitormanagement.saveVisitor(visitorEto);

    assertThat(visitorEtoResult.getName()).isEqualTo("Mary");
    assertThat(visitorEtoResult.getId()).isNotNull();

    this.visitormanagement.deleteVisitor(visitorEtoResult.getId());
  }

  @Test
  public void findVisitorsTest() {

    VisitorSearchCriteriaTo criteria = new VisitorSearchCriteriaTo();
    Pageable pageable = PageRequest.of(0, 100);
    criteria.setPageable(pageable);
    Page<VisitorEto> result = this.visitormanagement.findVisitors(criteria);

    assertThat(result).isNotNull();
  }
}
