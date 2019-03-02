package com.hlpp.clientcontact.web.webflow.validators;

import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RequestMapping;

    import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

    import org.apache.commons.logging.Log;
    import org.apache.commons.logging.LogFactory;
    import org.springframework.web.servlet.ModelAndView;

    @Controller
    public class GeneralHandler {
      private final Log logger = LogFactory.getLog(getClass());

      @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
      public ModelAndView handleException (NoSuchRequestHandlingMethodException ex) {
        ModelAndView mav = new ModelAndView();
        logger.error("Exception found: " + ex);
        return mav;
      }
    }