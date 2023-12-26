package com.cloud.connector.pojo;

import lombok.Data;
import org.springframework.data.domain.Sort;
@Data
public class CloudDataProcessPagReqPo {
	private int pageNo = 0;
	private int pageSize = 50;
	private Sort.Direction sortDirection = Sort.Direction.DESC;
	private String sortBy = "creationDate";
	private String extractionFlag;
	private Long podId;
}
