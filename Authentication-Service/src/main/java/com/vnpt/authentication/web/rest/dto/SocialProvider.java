package com.vnpt.authentication.web.rest.dto;

import lombok.Getter;

/**
 * @author Chinna
 * @since 26/3/18
 */
@Getter
public enum SocialProvider {

	FACEBOOK("facebook"), TWITTER("twitter"), LINKEDIN("linkedin"), GOOGLE("google"), GITHUB("github"), LOCAL("local");

	private final String providerType;

    SocialProvider(final String providerType) {
		this.providerType = providerType;
	}

}
