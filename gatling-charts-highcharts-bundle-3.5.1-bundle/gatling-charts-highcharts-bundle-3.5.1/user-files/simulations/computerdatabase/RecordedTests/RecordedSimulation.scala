
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0")

	val headers_10 = Map("Origin" -> "http://computer-database.gatling.io")



	val scn = scenario("RecordedSimulation")
		// Search
		.exec(http("request_0")
			.get("/computers"))
		.pause(8)
		.exec(http("request_1")
			.get("/computers?f=Macbook"))
		.pause(2)
		.exec(http("request_2")
			.get("/computers/89"))
		.pause(9)
		// Browse
		.exec(http("request_3")
			.get("/computers?p=1&n=10&s=name&d=asc"))
		.pause(3)
		.exec(http("request_4")
			.get("/computers?p=2&n=10&s=name&d=asc"))
		.pause(2)
		.exec(http("request_5")
			.get("/computers?p=3&n=10&s=name&d=asc"))
		.pause(2)
		.exec(http("request_6")
			.get("/computers?p=4&n=10&s=name&d=asc"))
		.pause(1)
		.exec(http("request_7")
			.get("/computers?p=5&n=10&s=name&d=asc"))
		.pause(2)
		.exec(http("request_8")
			.get("/computers?p=6&n=10&s=name&d=asc"))
		.pause(15)
		// Edit
		.exec(http("request_9")
			.get("/computers/new"))
		.pause(19)
		.exec(http("request_10")
			.post("/computers")
			.headers(headers_10)
			.formParam("name", "Nintendo Switch")
			.formParam("introduced", "2017-02-17")
			.formParam("discontinued", "")
			.formParam("company", "23"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}