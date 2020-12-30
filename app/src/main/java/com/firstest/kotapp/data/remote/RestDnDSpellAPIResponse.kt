package com.firstest.kotapp.data.remote

import com.firstest.kotapp.domain.entity.DnDSpell

data class RestDnDSpellAPIResponse (
    val results: List<DnDSpell>
    )