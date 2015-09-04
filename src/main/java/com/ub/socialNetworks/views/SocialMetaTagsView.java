package com.ub.socialNetworks.views;

/**
 * Created by maslov on 04.09.15.
 */
public class SocialMetaTagsView {
    private FaceBookMetaTagsView faceBookMetaTagsView = new FaceBookMetaTagsView();
    private DefaultMetaTagsView defaultMetaTagsView = new DefaultMetaTagsView();

    public SocialMetaTagsView(){

    }

    public SocialMetaTagsView(String title, String description, String imageUrl) {
        this.defaultMetaTagsView.setDescription(description);
        this.defaultMetaTagsView.setTitle(title);
        this.defaultMetaTagsView.setImage_src(imageUrl);

        this.faceBookMetaTagsView.setOgDescription(description);
        this.faceBookMetaTagsView.setOgImage(imageUrl);
        this.faceBookMetaTagsView.setOgTitle(title);
    }

    /**
     * https://developers.facebook.com/docs/sharing/best-practices
     * @param title  <meta property="og:title"
    content="Workday Sets Price Range for I.P.O." />
     * @param description <meta property="og:description" content="Workday, a provider of cloud-based
    applications for human resources, said on Monday that it would seek to price
    its initial public offering at $21 to $24 a share.  At the midpoint of that
    range, the offering would value the company at $3.6 billion. Like many other
    technology start-ups, Workday, founded in 2005, will have a dual-class share
    structure, with each Class B share having 10 votes. Its co-chief executives,
    David Duffield, the founder of PeopleSoft, and Aneel Bhusri, who was chief
    strategist at PeopleSoft, will have 67 percent of the voting rights after
    the I.P.O., according to the prospectus." />
     * @param url <meta property="og:url"
    content="http://www.myfavnews.com/2013/1/1/workday-price-range" />
     * @param image <meta property="og:image"
    content="http://graphics.myfavnews.com/images/logo-100x100.jpg" />
     * @param siteName <meta property="og:site_name" content="My Favorite News"/>
     */
    public SocialMetaTagsView(String title, String description, String url, String image, String siteName) {
        this.defaultMetaTagsView.setDescription(description);
        this.defaultMetaTagsView.setTitle(title);
        this.defaultMetaTagsView.setImage_src(image);

        this.faceBookMetaTagsView.setOgDescription(description);
        this.faceBookMetaTagsView.setOgImage(image);
        this.faceBookMetaTagsView.setOgTitle(title);
        this.faceBookMetaTagsView.setOgUrl(url);
        this.faceBookMetaTagsView.setOgSiteName(siteName);
    }

    public FaceBookMetaTagsView getFaceBookMetaTagsView() {
        return faceBookMetaTagsView;
    }

    public void setFaceBookMetaTagsView(FaceBookMetaTagsView faceBookMetaTagsView) {
        this.faceBookMetaTagsView = faceBookMetaTagsView;
    }

    public DefaultMetaTagsView getDefaultMetaTagsView() {
        return defaultMetaTagsView;
    }

    public void setDefaultMetaTagsView(DefaultMetaTagsView defaultMetaTagsView) {
        this.defaultMetaTagsView = defaultMetaTagsView;
    }
}
