//package com.code.secretary.controller.util;
//
//import java.util.Collections;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.code.secretary.entity.dto.util.UnifiedUploaderResponse;
//import com.code.secretary.entity.orm.util.Attachment;
//import com.code.secretary.service.util.AttachmentService;
//import com.code.secretary.utils.QueryUtilities;
//
//@RestController
//@RequestMapping("/api/util/attachments")
//@CrossOrigin(origins = "*")
//public class AttachmentController {
//
//	private AttachmentService attachmentService;
//
//	@Autowired
//	public AttachmentController(AttachmentService attachmentService) {
//		this.attachmentService = attachmentService;
//	}
//
//	/*********************** Attachment *******************************************/
//	/*---------------------- Operations --------------------------------------*/
//
//	@PostMapping()
//	public Attachment addAttachment(@RequestBody Attachment attachment) {
//		return this.attachmentService.saveAttachment(attachment);
//	}
//
//	@PutMapping()
//	public Attachment updateAttachment(@RequestBody Attachment attachment) {
//		return this.attachmentService.saveAttachment(attachment);
//	}
//
//	@DeleteMapping("/{id}")
//	public void deleteAttachment(@PathVariable(value = "id") Long id) {
//		this.attachmentService.deleteAttachment(id);
//	}
//
//	// TODO
//	@PostMapping("/saveUploadedAttachment")
//	public String saveUploadedAttachment(@RequestBody UnifiedUploaderResponse data) {
//		return this.attachmentService.saveUploadedAttachment(data);
//	}
//
//	/*---------------------- Queries -----------------------------------------*/
//	@GetMapping("/{entityAttachmentKey}/{pageIndex}/{pageSize}")
//	public Page<Attachment> getAttachmentsByEntityAttachmentKey(@PathVariable(value = "entityAttachmentKey") Long entityAttachmentKey, @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int pageSize) {
//		Pageable page = QueryUtilities.returnPageForQuery(pageIndex, pageSize, null);
//		return this.attachmentService.getAttachmentsByEntityAttachmentKey(entityAttachmentKey, page);
//	}
//
//	@GetMapping("/{attachmentId}")
//	public Attachment getAttachmentsById(@PathVariable(value = "attachmentId") Long attachmentId) {
//		return this.attachmentService.getAttachmentById(attachmentId);
//	}
//
//	@GetMapping("/next-entity-attachment-key")
//	public Long getNextEntityAttachmentKey() {
//		return this.attachmentService.getNextEntityAttachmentKey();
//	}
//
//	@GetMapping("/upload-path/{id}")
//	public Map<String, String> getUploadParameters(@PathVariable(value = "id") Long id, HttpServletRequest request) {
//		return Collections.singletonMap("response", this.attachmentService.getUploadPath(id, (request.getRequestURL().toString()).replace(request.getServletPath(), "")));
//	}
//
//	@GetMapping("/download-path/{id}")
//	public Map<String, String> getDownloadParameters(@PathVariable(value = "id") Long id) {
//		return Collections.singletonMap("response", this.attachmentService.getDownloadPath(id));
//	}
//}
