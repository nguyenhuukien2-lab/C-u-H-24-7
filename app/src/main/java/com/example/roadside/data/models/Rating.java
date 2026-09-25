package com.example.roadside.data.models;

import java.util.List;

public class Rating {
    private String id;
    private String requestId;
    private String providerId;
    private int stars;
    private List<String> highlightTags;
    private String comment;
    private String receiptPhotoUrl;
    private long submittedAt;

    public Rating() { }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }

    public int getStars() { return stars; }
    public void setStars(int stars) { this.stars = stars; }

    public List<String> getHighlightTags() { return highlightTags; }
    public void setHighlightTags(List<String> highlightTags) { this.highlightTags = highlightTags; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getReceiptPhotoUrl() { return receiptPhotoUrl; }
    public void setReceiptPhotoUrl(String receiptPhotoUrl) { this.receiptPhotoUrl = receiptPhotoUrl; }

    public long getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(long submittedAt) { this.submittedAt = submittedAt; }
}
