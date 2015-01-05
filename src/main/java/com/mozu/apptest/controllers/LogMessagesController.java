/*
 * COPYRIGHT (C) 2014 Volusion Inc. All Rights Reserved.
 */
package com.mozu.apptest.controllers;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozu.logger.model.LogMessage;
import com.mozu.logger.service.MongoDbLogService;

@Controller
@RequestMapping("/api/log")
public class LogMessagesController {

    @RequestMapping(value = "getLogMessages/{tenantId}", method = RequestMethod.GET)
    public @ResponseBody
    List<LogMessage> getLogMessages(@PathVariable int tenantId, 
            @RequestParam(value = "startRow", required = true) Integer startRow,
            @RequestParam(value = "startTime", required= false) String startTime)
            throws Exception {
        MongoDbLogService logService = new MongoDbLogService(this.getClass());
        List<LogMessage> messages = null;
        if (startTime==null) {
            messages = logService.get(tenantId, startRow, startRow+25);
        } else {
            // convert startTime to date
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
            DateTime dt = formatter.parseDateTime(startTime);
            Date date = new Date(dt.getMillis());
            messages = logService.get(tenantId, date, startRow, 25);
        }
        return messages;
    }

}
