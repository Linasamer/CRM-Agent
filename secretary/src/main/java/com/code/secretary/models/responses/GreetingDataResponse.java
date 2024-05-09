package com.code.secretary.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GreetingDataResponse {

	@JsonProperty("CICNumber")
	private String cicNumber;

	@JsonProperty("Title")
	private String title;

	@JsonProperty("FirstNameEN")
	private String firstNameEn;

	@JsonProperty("SecondNameEN")
	private String secondNameEn;

	@JsonProperty("ThirdNameEN")
	private String thirdNameEn;

	@JsonProperty("LastNameEN")
	private String lastNameEn;

	@JsonProperty("FirstNameAR")
	private String firstNameAr;

	@JsonProperty("SecondNameAR")
	private String secondNameAr;

	@JsonProperty("ThirdNameAR")
	private String thirdNameAr;

	@JsonProperty("LastNameAR")
	private String lastNameAr;

	@JsonProperty("CustSinceDt")
	private String custSinceDt;

	@JsonProperty("IDNumber")
	private String idNumber;

	@JsonProperty("IDType")
	private String idType;

	@JsonProperty("CustStatus")
	private String custStatus;

	@JsonProperty("IDIssueDate")
	private String idIssueDate;

	@JsonProperty("BirthDate")
	private String birthDate;

	@JsonProperty("Gender")
	private String gender;

}
