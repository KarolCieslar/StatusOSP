package pl.kcieslar.statusosp.screens.group_list

import pl.kcieslar.statusosp.model.objects.Group

data class GroupListUiState(
    val groupList: List<Group> = listOf(),
    val exception: Exception? = null
)