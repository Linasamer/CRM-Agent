package com.code.secretary.enums;

public enum ExternalWebservicesEnum {
	// Department
	SETUP_DEPARTMENTS_SEARCH_DEPARTMENTS("/departments?code={code}&name={name}"),
	SETUP_DEPARTMENTS_GET_DEPARTMENT_BY_ID("/departments/{id}"),
	SETUP_DEPARTMENTS_GET_FIRST_LEVEL_CHILDREN_BY_ID("/departments/getDepartmentFirstLevelChildren?id={id}&sort={sort}"),
	SETUP_DEPARTMENTS_GET_PRESIDENCY_DEPARTMENT("/departments/getPresidencyDepartment"),

	// Department Hierarchy
	SETUP_DEPARTMENT_GET_PARENT_DEPARTMENTS("/departments/getDepartmentParentsById?id={id}"),
	SETUP_DEPARTMENT_GET_CHILDREN_DEPARTMENTS("/departments/getDepartmentChildrensById?id={id}"),

	// Employee
	SETUP_EMPLOYEES_SEARCH_EMPLOYEES("/employees?name={name}&socialId={socialId}&militaryNumber={militaryNumber}&physicalUnitName={physicalUnitName}&physicalUnitId={physicalUnitId}&parentPhysicalUnitId={parentPhysicalUnitId}"),
	SETUP_EMPLOYEES_GET_EMPLOYEE_BY_ID("/employees/{id}"),
	SETUP_EMPLOYEES_GET_EMPLOYEE_BY_EMAIL("/employees/email/{email}"),
	SETUP_EMPLOYEES_GET_EMPLOYEE_BY_SOCIAL_ID("/employees/socialId/{socialId}"),

	// Employee Photo
	SETUP_EMPLOYEE_PHOTO_GET_EMPLOYEE_PHOTO("/employees/photo?employeeId={employeeId}"),

	// Hijri Calendar
	SETUP_HIJRI_CALENDAR_GET_HIJRI_SYS_DATE("/hijriCalendar"),
	SETUP_HIJRI_CALENDAR_HIJRI_TO_GREG("/hijriCalendar/hijriToGreg?hijriDate={hijriDate}"),
	SETUP_HIJRI_CALENDAR_GREG_TO_HIJRI("/hijriCalendar/gregToHijri?gregDate={gregDate}"),
	SETUP_HIJRI_CALENDAR_IS_VALID("/hijriDate/isValidHijri?hijriDate={hijriDate}"),
	SETUP_HIJRI_CALENDAR_IS_DATE_BETWEEN("/hijriCalendar/isDateBetween?startDate={startDate}&endDate={endDate]&checkDate={checkDate}"),

	// Security
	SECURITY_AUTHENTICATE_USER("/user?userName={userName}&password={password}&ldap={ldapFlag}"),
	SECURITY_EMPLOYEE_MENUS("/employee/menus?userId={userId}&menuType={menuType}&moduleCode={moduleCode}"),
	SECURITY_EMPLOYEE_MENU_ACTIONS("/employee/menu/actions?userId={userId}&menuCode={menuCode}"),
	SECURITY_IS_ACTION_GRANTED("/employee/menu/action/granted?userId={userId}&menuCode={menuCode}&actionCode={actionCode}");

	private String path;

	private ExternalWebservicesEnum(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
