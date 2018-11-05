package posts.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("catalogmapping/getcatalogmapping/json")
public class CatalogController {

  private static Logger logger = LoggerFactory.getLogger(CatalogController.class);

  @RequestMapping(value = "/{catalogId}/{siteID}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
  @ResponseBody
  protected ResponseEntity getById(@PathVariable("catalogId") long catalogId, @PathVariable("siteID") int siteID) {
    logger.info("get into getById method");
    Res res = new Res();
    res.setInvocationId("0");
    res.setResponseStatus("SUCCESS");
    res.setErrors(new ArrayList<>());
    CatData catData = new CatData();
    catData.setSiteID(siteID);
    catData.setCatalogId(catalogId);
    catData.setCategoryName("Monitors");
    catData.setCatalogName("ePD US SDC Monitors");
    catData.setCatalogEnabled(true);
    catData.setVcsId(2982);
    List<CatData> catDatas = new ArrayList<>();
    catDatas.add(catData);
    res.setMappingList(catDatas);
    return new ResponseEntity(res, HttpStatus.OK);
  }
}
