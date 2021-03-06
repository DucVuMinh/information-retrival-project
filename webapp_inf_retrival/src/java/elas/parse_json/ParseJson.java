/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elas.parse_json;

/**
 * This class is created to contain function for parsing parameter then creating
 * json string that be used to interact with elasticsearch
 *
 * @author ducvu
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ParseJson {

    /**
     * Method to create Json to indexing data to elasticsearch
     *
     * @param id is id of doc in elasticsearch
     * @return String json
     */
    public static String createIndex(int id) {
        JSONObject obj = new JSONObject();
        obj.put("_id", id);
        JSONObject objId = new JSONObject();
        objId.put("index", obj);
        return objId.toString();
    }

    /**
     * Method create Nested Json for overall search Json
     *
     * @param field string represent for field of document
     * @param input search input string
     * @param percentShouldMatch percent should match, parameter of
     * elasticsearch system
     * @param slop slop parameter of elasticsearch system
     * @return Json object
     */
    public static JSONObject createNestedQuery(String field,
            String input, int percentShouldMatch, int slop) {
        JSONObject objQuery = new JSONObject();
        objQuery.put("query", input);
        objQuery.put("minimum_should_match", String.valueOf(percentShouldMatch) + "%");
        objQuery.put("slop", slop);

        JSONObject objContent = new JSONObject();
        objContent.put(field, objQuery);

        JSONObject objMatch = new JSONObject();
        objMatch.put("match", objContent);
        return objMatch;
    }

    /**
     * create Json object for search, send this json to url:
     * http://localhost:9200/yahoo/news/_search?search_type=dfs_query_then_fetch
     *
     * @param input is search input string
     * @param percentShouldMatch percent should match, parameter of
     * elasticsearch system
     * @param slop slop parameter of elasticsearch system
     * @param pageNum is page number
     * @param numDocPerPage is number document per page
     * @return Json object
     */
    public static JSONObject createBestFieldsSearchQueryJson(
            String input, int percentShouldMatch, int slop, int pageNum, int numDocPerPage, float tieBreaker)
            throws Exception {
        if (slop < 1) {
            throw new Exception("slop must be greater than 0");
        }
        if (percentShouldMatch < 0 || percentShouldMatch > 100) {
            throw new Exception("number percent must be between 0 and 100");
        }
        if (tieBreaker < 0 | tieBreaker > 1) {
            throw new Exception("number tieBreaker must be a float between 0 and 1");
        }
        JSONObject content = createNestedQuery("content", input, percentShouldMatch, slop);
        JSONObject description = createNestedQuery("description", input, percentShouldMatch, slop);
        JSONObject title = createNestedQuery("title", input, percentShouldMatch, slop);
        JSONObject url = createNestedQuery("url", input, percentShouldMatch, slop);

        JSONArray queries = new JSONArray();
        queries.add(content);
        queries.add(description);
        queries.add(title);
        queries.add(url);

        JSONObject query = new JSONObject();
        query.put("queries", queries);
        query.put("tie_breaker", tieBreaker);

        JSONObject disMax = new JSONObject();
        disMax.put("dis_max", query);

        JSONObject queryOverAll = new JSONObject();
        queryOverAll.put("query", disMax);

        queryOverAll.put("from", pageNum);
        queryOverAll.put("size", numDocPerPage);
        return queryOverAll;
    }

    /**
     * Default method search, recommend for using After creating json for
     * searching, giving it to a restful api:
     * http://localhost:9200/yahoo/news/_search?search_type=dfs_query_then_fetch
     *
     * @param input search input string
     * @param pageNumber number page
     * @return a string
     * @throws Exception
     */
    public static String defaultSearch(String input, int pageNumber) throws Exception {
        String search = createBestFieldsSearchQueryJson(
                input, 30, 1, pageNumber, 5, (float) 0.2).toString();
        return search;
    }

    /**
     *Method for creating json string for match phrase query
     * @param feild
     * @param input
     * @return
     */
    public static JSONObject matchPhraseNestedFeild(String feild, String input) {
        JSONObject jsfeild = new JSONObject();
        jsfeild.put(feild, input);
        JSONObject matchPhrase = new JSONObject();
        matchPhrase.put("match_phrase", jsfeild);
        return matchPhrase;

    }
    
    /**
     * Create a string query searching phrase matching
     * Create an Ajax to http://localhost:9200/yahoo/news/_search
     * with body is generated from this method
     * @param input
     * @return 
     */
    public static String matchPharseQueryJson(String input) {
        JSONObject jsonQuery = new JSONObject();
        JSONObject bool = new JSONObject();
        JSONArray should = new JSONArray();
        JSONObject shouldJson = new JSONObject();
        should.add(matchPhraseNestedFeild("title", input));
        should.add(matchPhraseNestedFeild("description", input));
        should.add(matchPhraseNestedFeild("content", input));
        shouldJson.put("should", should);
        bool.put("bool", shouldJson);
        jsonQuery.put("query", bool);
        return jsonQuery.toJSONString();
    }
    /**
     * Method to get JSON body for getting a some suggestion for user.
     * Open Ajax with http://localhost:9200/yahoo/news/_search
     * Then set body is this string
     * @param input
     * @return 
     */
    public static String suggestorJson(String input){
        JSONObject suggest = new JSONObject();
        JSONObject inputText = new JSONObject();
        JSONObject simplePhrase = new JSONObject();
        JSONObject phrase = new JSONObject();
        JSONObject nestedPhrase = new JSONObject();
        nestedPhrase.put("analyzer", "english");
        nestedPhrase.put("field", "title");
        nestedPhrase.put("size", 3);
        nestedPhrase.put("real_word_error_likelihood", 0.95);
        nestedPhrase.put("max_errors", 0.5);
        nestedPhrase.put("gram_size", 2);
        phrase.put("phrase", nestedPhrase);
        simplePhrase.put("simple_phrase", phrase);
        simplePhrase.put("text", input);
        suggest.put("suggest", simplePhrase);
        return suggest.toJSONString();
        
    }
    public static void main(String[] args) {
        try {
            String s = matchPharseQueryJson("Uber CEO quits Trump advisory");
            System.out.println(s);
        } catch (Exception ex) {
            Logger.getLogger(ParseJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
