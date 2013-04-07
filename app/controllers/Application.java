package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import models.World;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.AsyncAppender;

import play.jobs.Job;
import play.libs.F;
import play.mvc.Controller;

public class Application extends Controller {

	private static final int TEST_DATABASE_ROWS = 10000;

	// FIXME: should this test be consistent - ie set seed or not?
	private static Random random = new Random();

	public static void index() {
		render();
	}

	public static void json() {
		// returns
		// {"_children":{"message":{"_value":"Hello World!"}},"_nodeFactory":{}}
		Map<String, String> result = new HashMap<String, String>();
		result.put("message", "Hello World!");
		renderJSON(result);
	}

	public static void db() throws InterruptedException, ExecutionException {
		final int queries;
		String queryCount = params.get("queries");
		if (StringUtils.isNotEmpty(queryCount)) {
			queries = Integer.parseInt(queryCount);
		} else
			queries = 1;
		final List<World> worlds = new ArrayList<World>();
		Job<List<World>> job = new Job<List<World>>() {
			public java.util.List<World> doJobWithResult() throws Exception {
				for (int i = 0; i < queries; ++i) {
					Long id = Long
							.valueOf(random.nextInt(TEST_DATABASE_ROWS) + 1);
					World result = World.findById(id);
					worlds.add(result);
				}
				return worlds;
			};
		};
		List<World> result = job.now().get();
		renderJSON(result);
	}

	public static void db_sync() {
		Integer queries = 1;
		String queryCount = params.get("queries");
		if (StringUtils.isNotEmpty(queryCount)) {
			queries = Integer.parseInt(queryCount);
		}
		final List<World> worlds = new ArrayList<World>();
		for (int i = 0; i < queries; ++i) {
			Long id = Long.valueOf(random.nextInt(TEST_DATABASE_ROWS) + 1);
			World result = World.findById(id);
			worlds.add(result);
		}
		renderJSON(worlds);
	}
}