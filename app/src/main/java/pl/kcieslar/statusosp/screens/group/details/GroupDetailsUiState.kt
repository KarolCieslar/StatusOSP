package pl.kcieslar.statusosp.screens.group.details

import pl.kcieslar.statusosp.model.objects.Group
import pl.kcieslar.statusosp.model.service.enums.DataStatus

data class GroupDetailsUiState(
    val group: Group? = null,
    val exception: Exception? = null,
    val dataStatus: DataStatus = DataStatus.LOADING
)