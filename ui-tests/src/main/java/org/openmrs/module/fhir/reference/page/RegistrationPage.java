package org.openmrs.module.fhir.reference.page;

import org.openmrs.module.fhir.reference.helper.TestPatient;
import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The register-a-new-patient page.
 */
public class RegistrationPage extends Page {


	public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl() {
        return null;
    }

    static final By CONTACT_INFO_SECTION = By.id("contactInfo_label");
	static final By CONFIRM_SECTION = By.id("confirmation_label");
	static final By BIRTHDATE_LABEL = By.id("birthdateLabel");
    static final By PHONE_NUMBER_LABEL = By.id("phoneNumberLabel");
	static final By GIVEN_NAME = By.name("givenName");
	static final By FAMILY_NAME = By.name("familyName");
    static final By MIDDLE_NAME = By.name("middleName");
    static final String GENDER_FIELD_ID = "gender-field";
    static final By GENDER = By.id(GENDER_FIELD_ID);
    static final String BIRTHDAY_DAY_TEXTBOX_ID = "birthdateDay-field";
    static final By BIRTHDAY_DAY = By.id(BIRTHDAY_DAY_TEXTBOX_ID);
    static final By BIRTHDAY_MONTH = By.id("birthdateMonth-field");
    static final By BIRTHDAY_YEAR = By.id("birthdateYear-field");
	static final By ADDRESS1 = By.id("address1");
	static final By ADDRESS2 = By.id("address2");
	static final By CITY_VILLAGE = By.id("cityVillage");
    static final By STATE_PROVINCE = By.id("stateProvince");
    static final By COUNTRY = By.id("country");
    static final By POSTAL_CODE = By.id("postalCode");
    static final By PHONE_NUMBER = By.name("phoneNumber");
    static final By UNKNOWN_PATIENT = By.id("checkbox-unknown-patient");
    
    // These xpath expressions should be replaced by id's or cssSelectors if possible.
    static final String CONFIRMATION_DIV = "//div[@id='confirmation']";
	static final By NAME_CONFIRM = By.xpath(CONFIRMATION_DIV + "//li[1]/strong");
	static final By GENDER_CONFIRM = By.xpath(CONFIRMATION_DIV + "//li[2]/strong");
	static final By BIRTHDATE_CONFIRM = By.xpath(CONFIRMATION_DIV + "//li[3]/strong");
	static final By ADDRESS_CONFIRM = By.xpath(CONFIRMATION_DIV + "//li[4]/strong");
	static final By PHONE_CONFIRM = By.xpath(CONFIRMATION_DIV + "//li[5]/strong");

	static final By PATIENT_HEADER = By.className("patient-header");
	static final By CONFIRM = By.cssSelector("input[value='Confirm']");

	public void enterPatient(TestPatient patient) {
        enterPatientGivenName(patient.givenName);
        enterPatientMiddleName("");  // no middle name
        enterPatientFamilyName(patient.familyName);
        clickOnGenderLink();
        selectPatientGender(patient.gender);
        clickOnBirthDateLink();
        enterPatientBirthDate(patient);
        clickOnContactInfo();
        enterPatientAddress(patient);
        clickOnPhoneNumber();
        enterPhoneNumber(patient.phone);
        clickOnConfirmSection();
    }

    public void enterUnidentifiedPatient(TestPatient patient) {
        selectUnidentifiedPatient();
        clickOnGenderLink();
        selectPatientGender(patient.gender);
        clickOnConfirmSection();
    }

	public void enterPatientAddress(TestPatient patient) {
        setText(ADDRESS1, patient.address1);
        setText(ADDRESS2, patient.address2);
        setText(CITY_VILLAGE, patient.city);
        setText(STATE_PROVINCE, patient.state);
        setText(COUNTRY, patient.country);
        setText(POSTAL_CODE, patient.postalCode);
    }

    public void enterPatientBirthDate(TestPatient patient) {
        setText(BIRTHDAY_DAY, patient.birthDay);
        selectFrom(BIRTHDAY_MONTH, patient.birthMonth);
        setText(BIRTHDAY_YEAR, patient.birthYear);
    }

    public void selectUnidentifiedPatient() {
        clickOn(UNKNOWN_PATIENT);
    }

    public void selectPatientGender(String gender) {
        selectFrom(GENDER, gender);
    }

    public void enterPatientFamilyName(String familyName) {
        setText(FAMILY_NAME, familyName);
    }

    public void enterPatientGivenName(String givenName) {
		setText(GIVEN_NAME, givenName);
    }

    public void enterPatientMiddleName(String middleName) {
        setText(MIDDLE_NAME, middleName);
    }

    public void clickOnContactInfo() {
        clickOn(CONTACT_INFO_SECTION);
    }

    public void clickOnPhoneNumber() {
        clickOn(PHONE_NUMBER_LABEL);
    }

	public void enterPhoneNumber(String phone) {
        setText(PHONE_NUMBER, phone);
    }

    public void clickOnConfirmSection() {
        clickOn(CONFIRM_SECTION);
    }

    public void clickOnGenderLink() {
    	waitForFocusById(GENDER_FIELD_ID);
    }

    public void clickOnBirthDateLink() {
        clickOn(BIRTHDATE_LABEL);
        waitForFocusById(BIRTHDAY_DAY_TEXTBOX_ID);
    }

	public String getNameInConfirmationPage() {
        return getText(NAME_CONFIRM) ;
    }

    public String getGenderInConfirmationPage() {
    	return getText(GENDER_CONFIRM) ;
    }
    
    public String getBirthdateInConfirmationPage() {
    	return getText(BIRTHDATE_CONFIRM) ;
    }
    
    public String getAddressInConfirmationPage() {
    	return getText(ADDRESS_CONFIRM) ;
    }
    
    public String getPhoneInConfirmationPage() {
    	return getText(PHONE_CONFIRM) ;
    }

    public String expectedUrlPath() {
	    return properties.getWebAppUrl() + "/registrationapp/registerPatient.page?appId=referenceapplication.registrationapp.registerPatient";
    }

	public void confirmPatient() {
		clickOn(CONFIRM);
		waitForElement(PATIENT_HEADER);
    }
}
