<template>
  <div class="tag-selection">
    <h2>태그선택</h2>
      <template v-for="tagType, index in tags.tagTypeList" :key="index">
        <div class="tag-group"></div>
          <strong>{{ tagType.tagTypeName }} : </strong>
        <div>
          <template v-for="tag, index in tags.tagList" :key="index">
              <button :class="{ 'selected': isSelected(tag.tagNo) }"
                      v-if="tag.keyTypeNo == tagType.tagTypeNo" @click="toggleSelection(tag.tagNo)">
                {{ tag.tagName }}
              </button>
          </template>
        </div>
      </template>
    <br><br><br>
    <button @click="saveSelections">저장</button>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/users'
import { $getTags, $addUserTags } from '@/api/tag'
import { useRoute, useRouter } from 'vue-router'

export default {
  name: 'TagRegisterComp',
  setup () {
    const tags = ref({})

    const router = useRouter()

    function getTags () {
      $getTags()
        .then(res => {
          console.log('tags data', res.data)
          tags.value = res.data
        })
        .catch(err => {
          console.log('error', err)
        })
    }

    const user = useUserStore()

    const selectedTags = ref([])

    // const toggleSelection = (item) => {
    //   if (selectedTags.value.includes(item)) {
    //     // 이미 내부에 존재하는 경우, 값을 삭제합니다.
    //     selectedTags.value.splice(selectedTags.value.indexOf(item), 1)
    //     event.target.classList.remove('selected')
    //   } else {
    //     // 내부에 존재하지 않는 경우, 값을 추가합니다.
    //     selectedTags.value.push(Math.round(item))
    //     event.target.classList.add('selected')
    //   }
    // }

    function toggleSelection (tagNo) {
      const index = selectedTags.value.indexOf(tagNo)
      if (index === -1) {
      // 내부에 존재하지 않는 경우, 값을 추가합니다.
        selectedTags.value.push(tagNo)
      } else {
        selectedTags.value.splice(index, 1)
      }
    }

    function isSelected (tagNo) {
      return selectedTags.value.includes(tagNo)
    }

    const saveSelections = () => {
      console.log('저장 하기 전 테스트')
      console.log(selectedTags.value)
      let isValid = true
      if (selectedTags.value.length === 0) {
        isValid = false
      }

      if (isValid) {
        console.log('선택한 항목: ' + selectedTags.value)
        console.log(user.getLoginUserInfo.userNo)
        $addUserTags(user.getLoginUserInfo.userNo, { tagNoList: selectedTags.value })
          .then(res => {
            console.log(res.data)
            alert('태그 저장이 완료되었습니다.')
            router.push('/')
          })
      } else {
        alert('각 항목별로 최소 1개 이상 선택해 주세요.')
      }
    }
    const tagList = user.getUserTagsInfo

    function logTagList () {
      if (tagList != null) {
        tagList.value.forEach(element => {
          console.log(element.tagNo)
          selectedTags.value.push(Math.round(element.tagNo))
        })
        console.log(tagList.value)
        console.log(selectedTags.value)
      }
    }

    onMounted(() => {
      logTagList()
      getTags()
    })

    return {
      selectedTags,
      toggleSelection,
      saveSelections,
      tags,
      isSelected
    }
  }
}
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

.selected {
  background-color: rgb(250, 150, 0);
  color: white;
}
</style>
