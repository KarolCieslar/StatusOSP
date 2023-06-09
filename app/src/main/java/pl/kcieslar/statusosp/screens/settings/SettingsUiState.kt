package pl.kcieslar.statusosp.screens.settings

import pl.kcieslar.statusosp.model.objects.Group

data class SettingsUiState(
    val groupList: List<Group> = listOf(),
    val exception: Exception? = null
)