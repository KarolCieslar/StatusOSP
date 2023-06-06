package pl.kcieslar.statusosp.model.service.responses

import pl.kcieslar.statusosp.model.objects.Group

data class UserGroupsResponse(
    var list: List<Group>? = null,
    var exception: Exception? = null
)