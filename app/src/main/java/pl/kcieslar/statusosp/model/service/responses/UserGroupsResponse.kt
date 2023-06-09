package pl.kcieslar.statusosp.model.service.responses

import pl.kcieslar.statusosp.model.objects.Group

data class GroupResponse(
    var group: Group? = null,
    var exception: Exception? = null
)