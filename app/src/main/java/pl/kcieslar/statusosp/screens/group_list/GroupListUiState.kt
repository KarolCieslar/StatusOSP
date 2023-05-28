package pl.kcieslar.statusosp.screens.group_list

data class GroupListUiState(
    val groupName: String = "",
    val groupDescription: String = "",
    val isError: Boolean = false
)