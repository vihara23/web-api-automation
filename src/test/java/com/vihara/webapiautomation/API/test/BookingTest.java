package com.vihara.webapiautomation.API.test;

import com.vihara.webapiautomation.API.service.BookingService;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

@Log
public class BookingTest {

    public BookingService bookingService;
    public HashMap<String, String> headers;
    public String relativeURI;
    public String body;

    public Logger logger;

    @BeforeMethod
    public void setUp() {
        try {
            logger = Logger.getLogger(BookingTest.class);
            bookingService = new BookingService();
            headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            relativeURI = "/booking";
            body = """
                    {
                    \t"firstname": "Jim",
                    \t"lastname": "Brown",
                    \t"totalprice": 1500,
                    \t"depositpaid": true,
                    \t"bookingdates": {
                    \t\t"checkin": "2022-12-15",
                    \t\t"checkout": "2022-12-01"
                    \t},
                    \t"additionalneeds": "Breakfast"
                    }""";
        } catch (Exception e) {
            logger.info("setup() Failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 1)
    public void createBookingVerify() throws Exception {
        try {

            Response response = bookingService.post(relativeURI, headers, body);

            Assert.assertEquals(response.statusCode(), 200);

            response.prettyPeek();

            Assert.assertNotNull(response.path("bookingid"));
            Assert.assertEquals(response.path("booking.firstname"), "Jim");
            Assert.assertEquals(response.path("booking.lastname"), "Brown");
            Assert.assertEquals((Integer) response.path("booking.totalprice"), 1500);
            Assert.assertTrue((Boolean) (response.path("booking.depositpaid")));
            Assert.assertEquals(response.path("booking.bookingdates.checkin"), "2022-12-15");
            Assert.assertEquals(response.path("booking.bookingdates.checkout"), "2022-12-01");
            Assert.assertEquals(response.path("booking.additionalneeds"), "Breakfast");

        } catch (Exception e) {
            log.info("getAllBookings() Failed!!");
            throw e;
        }
    }

    @Test(priority = 2)
    public void createBookingInvalidURIVerify() throws Exception {
        try {
            relativeURI = "book";
            Response response = bookingService.post(relativeURI, headers, body);

            Assert.assertEquals(response.statusCode(), 404);

        } catch (Exception e) {
            log.info("createBookingInvalidURIVerify() Failed!!");
            throw e;
        }
    }

    @Test(priority = 3)
    public void createBookingWithoutHeadersIVerify() throws Exception {
        try {
            relativeURI = "/booking";
            headers.clear();
            Response response = bookingService.post(relativeURI, headers, body);

            Assert.assertEquals(response.statusCode(), 500);

        } catch (Exception e) {
            log.info("createBookingInvalidURIVerify() Failed!!");
            throw e;
        }
    }

    @Test(priority = 4)
    public void createBookingWithoutBodyIVerify() throws Exception {
        try {
            relativeURI = "/booking";
            headers.put("Content-Type", "application/json");
            body = "";
            Response response = bookingService.post(relativeURI, headers, body);

            Assert.assertEquals(response.statusCode(), 500);

        } catch (Exception e) {
            log.info("createBookingInvalidURIVerify() Failed!!");
            throw e;
        }
    }
}
