var PREF, SUFF [10000]uint64

func pack(count int, fix, mask uint32) uint64 {
	return uint64(count)<<48 | uint64(fix)<<32 | uint64(mask)
}

func unpack(v uint64) (count int, fix, mask uint32) {
	return int(v >> 48), uint32(v >> 32) & 0xFFFF, uint32(v)
}

func maxPartitionsAfterOperations(s string, k int) int {
	if k == 26 {
		return 1
	}
	var uniqueletters uint32
	for i := range s {
		uniqueletters |= 1 << (s[i] - 'a')
	}
	if bits.OnesCount32(uniqueletters) < k {
		return 1
	}
	n := len(s)
	// prefix and suffix processing
	process := func(FIX *[10000]uint64, start, end, step int) {
		var fix, mask uint32
		var count int
		for i := start; i != end; i += step {
			c := s[i] - 'a'
			newbit := 1 &^ int(mask>>c)
			count += newbit
			bit := uint32(1 << c)
			mask |= bit
			if count > k {
				fix++
				count = 1
				mask = bit
			}
			FIX[i+step] = pack(count, fix, mask)
		}
	}
	process(&PREF, 0, n-1, 1)
	process(&SUFF, n-1, 0, -1)
    SUFF[n-1] = 0
	// calculate result
	var result uint32
	for i := range n {
		pcount, pref, pmask := unpack(PREF[i])
		scount, suff, smask := unpack(SUFF[i])
		val := pref + suff + 1
		count := bits.OnesCount32(pmask | smask)
		if pcount == k && scount == k && count < 26 {
			val += 2
		} else if count >= k {
			val += 1
		}
		result = max(result, val)
	}
	return int(result)
}
