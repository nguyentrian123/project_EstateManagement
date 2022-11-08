package com.laptrinhjavaweb.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.utils.VaidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.MessageUtils;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

	@Autowired
	private IBuildingService buildingService;
	
	@Autowired
	private IUserService userService;

	
	@RequestMapping(value="/admin/buildinglist",method = RequestMethod.GET)
	public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchDTO searchDTO ) {
		
		ModelAndView mav = new ModelAndView("admin/building/building_list");
        VaidationUtils.validatePage(searchDTO,mav);
        if(searchDTO.getPage() > 0) {
        	Pageable pageable = PageRequest.of(searchDTO.getPage() , 10);
    		ResponseDTO responseDTO = buildingService.findBuilding(searchDTO,pageable);
    		mav.addObject(SystemConstant.MODEL, responseDTO);
    		mav.addObject("staffmaps", userService.getUserMaps());
    		mav.addObject("districtmaps", buildingService.getDistricts());
    		mav.addObject("typemaps",buildingService.getBuildingTypes());
        }	
		return mav;
		
	}
	


	@RequestMapping(value = "/admin/buildingedit", method = RequestMethod.GET)
	public ModelAndView addBuilding( HttpServletRequest request) { 
		ModelAndView mav = new ModelAndView("admin/building/building_edit");
		BuildingDTO model = new BuildingDTO();

		MessageUtils.displayMessage(mav,request);
		mav.addObject(SystemConstant.MODEL, model); // ta có các key của  district và type, sau khi chạy xong thì nó sẽ nhận giá trị với key đã có
		mav.addObject("districtmaps", buildingService.getDistricts());
		mav.addObject("typemaps",buildingService.getBuildingTypes());
		return mav;
	}
	
	
	// vi springsecurity no phan biet thong qua URL nen phai tach ra 2 ham
	@RequestMapping(value = "/admin/buildingedit-{id}", method = RequestMethod.GET)
	public ModelAndView editBuilding(@PathVariable(value = "id") Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/building/building_edit");
		BuildingDTO model = new BuildingDTO();
		model = buildingService.findById(id);

		MessageUtils.displayMessage(mav,request);
		mav.addObject(SystemConstant.MODEL, model);
		mav.addObject("districtmaps", buildingService.getDistricts());
		mav.addObject("typemaps",buildingService.getBuildingTypes());
		return mav;
	}
	
	
	@RequestMapping(value = "/admin/buildingdetail", method = RequestMethod.GET)
	public ModelAndView buildingDetail(@RequestParam(value = "id") Long id, 
										HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/building/building_detail");
		BuildingDTO model = new BuildingDTO();
		model = buildingService.findById(id);
		mav.addObject(SystemConstant.MODEL, model); 
		mav.addObject("districtmaps", buildingService.getDistricts());
		mav.addObject("typemaps",buildingService.getBuildingTypes());
		return mav;
		
	}
	
	
	
	
}
