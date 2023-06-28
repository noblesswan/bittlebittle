<template>
    <div class="tag-selection">
      <h2>태그선택</h2>
      <div class="tag-group">
        <strong>도수 :</strong>
        <button v-for="(range, index) in alcoholRanges" :key="index" @click="toggleSelection('alcoholRange', range)">{{ range }}</button>
      </div>
      <div class="tag-group">
        <strong>맛 :</strong>
        <button v-for="(taste, index) in tastes" :key="index" @click="toggleSelection('taste', taste)">{{ taste }}</button>
      </div>
      <div class="tag-group">
        <strong>술종류 :</strong>
        <button v-for="(type, index) in alcoholTypes" :key="index" @click="toggleSelection('alcoholType', type)">{{ type }}</button>
      </div>
      <div class="tag-group">
        <strong>탄산 :</strong>
        <button v-for="(carbonation, index) in carbonations" :key="index" @click="toggleSelection('carbonation', carbonation)">{{ carbonation }}</button>
      </div>
      <div class="tag-group">
        <strong>원산지 :</strong>
        <button v-for="(origin, index) in origins" :key="index" @click="toggleSelection('origin', origin)">{{ origin }}</button>
      </div>
      <div class="tag-group">
        <strong>목적 :</strong>
        <button v-for="(purpose, index) in purposes" :key="index" @click="toggleSelection('purpose', purpose)">{{ purpose }}</button>
      </div>
      <button @click="saveSelections">저장</button>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  
  export default {
    setup() {
      const alcoholRanges = [
        '5도 이하',
        '5도 초과 10도 이하',
        '10도 초과 15도 이하',
        '15도 초과 20도 이하',
        '20도 초과 25도 이하',
        '25도 초과 30도 이하',
        '30도 초과 35도 이하',
        '35도 초과'
      ];
  
      const tastes = ['달콤함', '시원함', '쓸쓸함'];
  
      const alcoholTypes = [
        '맥주',
        '소주',
        '청주',
        '탁주',
        '와인',
        '위스키',
        '블랜디',
      ];
  
      const carbonations = ['유', '무'];
  
      const origins = [
        '대한민국',
        '중국',
        '일본',
        '미국',
        '네덜란드',
        '폴란드',
        '벨기에',
        '프랑스',
        '스페인',
        '독일',
        '아일랜드',
        '이탈리아',
        '캐나다',
        '칠레',
        '페루',
        '기타',
      ];
  
      const purposes = ['파티', '집들이선물', '이별', '데이트','피크닉'];

      const selectedTags = ref({
  alcoholRange: [],
  taste: [],
  alcoholType: [],
  carbonation: [],
  origin: [],
  purpose: [],
});

const toggleSelection = (category, item) => {
  const index = selectedTags.value[category].indexOf(item);
  if (index > -1) {
    selectedTags.value[category].splice(index, 1);
  } else {
    selectedTags.value[category].push(item);
  }
};

const saveSelections = () => {
  let isValid = true;

  for (const category in selectedTags.value) {
    if (selectedTags.value[category].length === 0) {
      isValid = false;
      break;
    }
  }

  if (isValid) {
    // 여기에서 SQL로 저장하는 코드를 작성해 주세요.
    console.log('선택한 항목:', selectedTags.value);
  } else {
    alert('각 항목별로 최소 1개 이상 선택해 주세요.');
  }
};

return {
  alcoholRanges,
  tastes,
  alcoholTypes,
  carbonations,
  origins,
  purposes,
  selectedTags,
  toggleSelection,
  saveSelections,
};

},
};
</script>

<style scoped>
.tag-selection {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.tag-group {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.tag-group strong {
  margin-right: 10px;
}

button {
  margin-right: 5px;
  padding: 5px 10px;
  border: 1px solid #ccc;
  background-color: #fff;
  cursor: pointer;
}

button:hover {
  background-color: #eee;
}
</style>