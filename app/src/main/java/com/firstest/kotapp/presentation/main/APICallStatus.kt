package com.firstest.kotapp.presentation.main

import com.firstest.kotapp.domain.entity.DnDSpell

sealed class APICallStatus

data class APICallSuccess(val spellList: List<DnDSpell>) : APICallStatus()

object APICallFailure : APICallStatus()