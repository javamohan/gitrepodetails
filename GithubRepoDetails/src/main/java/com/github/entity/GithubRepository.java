package com.github.entity;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@ToString
public class  GithubRepository {

	@Id
    private String id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("clone_url")
    private String cloneUrl;
    @JsonProperty("stargazers_count")
    private int stars;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public String getDescription() {
        return description;
    }

    public int getStars() {
        return stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    private String createdAt;


    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
 	public int hashCode() {
 		return Objects.hash(cloneUrl, createdAt, description, fullName, id, stars);
 	}

 	@Override
 	public boolean equals(Object obj) {
 		if (this == obj)
 			return true;
 		if (obj == null)
 			return false;
 		if (getClass() != obj.getClass())
 			return false;
 		GithubRepository other = (GithubRepository) obj;
 		return Objects.equals(cloneUrl, other.cloneUrl) && Objects.equals(createdAt, other.createdAt)
 				&& Objects.equals(description, other.description) && Objects.equals(fullName, other.fullName)
 				&& Objects.equals(id, other.id) && stars == other.stars;
 	}

}
