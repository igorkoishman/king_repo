package posts.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CatData {

  private int siteID;
  private long categoryId;
  private long catalogId;
  private String categoryName;
  private String catalogName;
  private boolean isCatalogEnabled;
  private long vcsId;

  public int getSiteID() {
    return siteID;
  }

  public void setSiteID(int siteID) {
    this.siteID = siteID;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public long getCatalogId() {
    return catalogId;
  }

  public void setCatalogId(long catalogId) {
    this.catalogId = catalogId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCatalogName() {
    return catalogName;
  }

  public void setCatalogName(String catalogName) {
    this.catalogName = catalogName;
  }

  public boolean isCatalogEnabled() {
    return isCatalogEnabled;
  }

  public void setCatalogEnabled(boolean catalogEnabled) {
    isCatalogEnabled = catalogEnabled;
  }

  public long getVcsId() {
    return vcsId;
  }

  public void setVcsId(long vcsId) {
    this.vcsId = vcsId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CatData)) {
      return false;
    }

    CatData catData = (CatData) o;

    if (siteID != catData.siteID) {
      return false;
    }
    if (categoryId != catData.categoryId) {
      return false;
    }
    if (catalogId != catData.catalogId) {
      return false;
    }
    if (isCatalogEnabled != catData.isCatalogEnabled) {
      return false;
    }
    if (vcsId != catData.vcsId) {
      return false;
    }
    if (categoryName != null ? !categoryName.equals(catData.categoryName) : catData.categoryName != null) {
      return false;
    }
    return catalogName != null ? catalogName.equals(catData.catalogName) : catData.catalogName == null;
  }

  @Override
  public int hashCode() {
    int result = siteID;
    result = 31 * result + (int) (categoryId ^ (categoryId >>> 32));
    result = 31 * result + (int) (catalogId ^ (catalogId >>> 32));
    result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
    result = 31 * result + (catalogName != null ? catalogName.hashCode() : 0);
    result = 31 * result + (isCatalogEnabled ? 1 : 0);
    result = 31 * result + (int) (vcsId ^ (vcsId >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("siteID", siteID).append("categoryId", categoryId).append("catalogId", catalogId)
        .append("categoryName", categoryName).append("catalogName", catalogName).append("isCatalogEnabled", isCatalogEnabled)
        .append("vcsId", vcsId).toString();
  }
}
