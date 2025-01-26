package com.BI.service;

import com.BI.dto.ResponseDto.ProspectiveResponseDto;

public interface IProspectiveService {
     ProspectiveResponseDto getProspective(Integer userId,String month);
}
