package com.spring.bittlebittle.tag.dao;

import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.FoodTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

import java.util.List;

public interface TagDao {

	List<TagType> selectAllTagType();

	List<Tag> selectAllTag();

	List<Tag> selectTagByBottle(int bottleNo);

	void deleteBottleTag(int bottleNo);

	void insertBottleTag(List<BottleTag> bottleTagList);

	void insertTagType(String tagTypeName);

	void updateTagType(TagType tagType);

	void deleteTagType(int tagTypeNo);

	void insertTag(Tag tag);

	void updateTag(Tag tag);

	void deleteTag(int tagNo);

	void insertFoodTag(List<FoodTag> foodTagList);

	List<Tag> selectTagByFood(int foodNo);

	void deleteFoodTag(int foodNo);


}
