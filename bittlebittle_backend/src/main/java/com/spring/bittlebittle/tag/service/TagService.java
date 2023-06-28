package com.spring.bittlebittle.tag.service;

import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.FoodTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

import java.util.List;
import java.util.Map;

public interface TagService {

	List<TagType> getAllTagTypes();

	List<Tag> getAllTags();

	List<Tag> getTagsByBottle(int bottleNo);

	void editBottleTag(int bottleNo, List<BottleTag> taglist);

	void addBottleTag(List<BottleTag> tagList);

	void removeBottleTag(int bottleNo);
	
	List<TagType> addTagType(String tagTypeName);

	List<TagType> editTagType(TagType tagType);

	List<TagType> removeTagType(int tagTypeNo);

	List<Tag> addTag(Tag tag);

	List<Tag> editTag(Tag tag);

	List<Tag> removeTag(int tagNo);

	void addFoodTag(List<FoodTag> foodTagList);

	List<Tag> getTagsByFood(int foodNo);

	void editFoodTag(int foodNo, List<FoodTag> foodTagList);

	void removeFoodTag(int foodNo);


    Map<String, Object> getTags();
}
