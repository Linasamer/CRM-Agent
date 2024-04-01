//package com.code.secretary.service.security;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.code.secretary.entity.dto.security.Menu;
//import com.code.secretary.enums.ConfigurationEnum;
//import com.code.secretary.enums.ExternalServerEnum;
//import com.code.secretary.enums.ExternalWebservicesEnum;
//import com.code.secretary.enums.security.ModuleCodeEnum;
//import com.code.secretary.service.RestClientService;
//import com.code.secretary.service.setup.ConfigurationService;
//
//@Service
//public class SecurityServiceImpl implements SecurityService {
//
//	@Value("${setup.down}") // TODO: remove
//	private boolean setupDown;
//
//	private ConfigurationService configurationService;
//
//	public SecurityServiceImpl(ConfigurationService configurationService) {
//		this.configurationService = configurationService;
//	}
//
//	public boolean authenticateUser(String username, String password, boolean ldapFlag) {
//		if (setupDown) {
//			return true;
//		} else {
//			return RestClientService.getObject(ExternalServerEnum.SECURITY, ExternalWebservicesEnum.SECURITY_AUTHENTICATE_USER.getPath(), Boolean.class, username, password, ldapFlag);
//		}
//	}
//
//	@Override
//	public List<Menu> getMenusByEmployeeIdAndMenuClassification(Long employeeId, Integer menuClassification) {
//		if (setupDown) {
//
//			List<Menu> menus = new ArrayList<Menu>();
//			if (menuClassification == 1) {
//				menus.add(new Menu(1L, 1, "إعدادات النظام", "Setup", "title_setup", 1, null, null));
//				menus.add(new Menu(5L, 1, "التنظيم الإداري", "Administrative Organization", "title_administrativeOrganization", 1, 1L, "/setup/administrative-organization"));
//
//			} else if (menuClassification == 2) {
//				menus.add(new Menu(11L, 2, "الإجراءات", "Transactions", "title_workflowTransactions", 1, null, null));
//				menus.add(new Menu(12L, 2, "الإجراءات الواردة", "Tasks Inbox", "title_wfTasksInbox", 1, 11L, "/workflow/wf-inbox/1"));
//				menus.add(new Menu(13L, 2, "الإشعارات الواردة", "Notification Inbox", "title_wfNotificationInbox", 1, 11L, "/workflow/wf-inbox/2"));
//				menus.add(new Menu(14L, 2, "الإجراءات الصادرة", "Workflow Outbox", "title_wfOutbox", 1, 11L, "/workflow/wf-outbox"));
//			}
//			return menus;
//		} else {
//			return RestClientService.getList(ExternalServerEnum.SECURITY, ExternalWebservicesEnum.SECURITY_EMPLOYEE_MENUS.getPath(), Menu[].class, employeeId, menuClassification, ModuleCodeEnum.MODULE_CODE_IN_SECURITY.getCode());
//		}
//	}
//
//	@Override
//	public boolean isMenuActionGranted(Long employeeId, String action) {
//		if (setupDown)
//			return true;
//		else
//			return RestClientService.getObject(ExternalServerEnum.SECURITY, ExternalWebservicesEnum.SECURITY_IS_ACTION_GRANTED.getPath(), Boolean.class, employeeId, "", action);
//	}
//
//	@Override
//	public boolean isSystemAdmin(String employeeEmail) {
//		if (employeeEmail != null && !employeeEmail.isEmpty()
//				&& employeeEmail.equalsIgnoreCase(configurationService.getStringByCode(ConfigurationEnum.ADMIN_USER))) {
//			return true;
//		}
//		return false;
//	}
//}
