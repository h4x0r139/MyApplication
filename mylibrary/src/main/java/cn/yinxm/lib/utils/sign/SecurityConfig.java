/*
 * Copyright 2015 Rocko (http://rocko.xyz) <rocko.zxp@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.yinxm.lib.utils.sign;


import cn.yinxm.lib.utils.LogUtil;
import cn.yinxm.lib.utils.StringUtil;
import cn.yinxm.lib.utils.sign.util.DESUtils;

public class SecurityConfig {
	private static String desKey = null;
	private static String rsa_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDF3ygQ81LiML59SKeNS8XUTpudvG9k0fqi53MGmBNdHp/BaYn32XXOzHg/dKTAWDwNahFl+1lRO5RrUzd3yTR1YT7RBcDzRiWL/HuegmXcQnXMML+qaKMoh3Ovnt87KWM/zkXzJ6Cd2UtFkMJV62ceSthaFy5b2V0jiODnO4S60QIDAQAB";
	static {
		try {
			desKey = DESUtils.initKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDesKey() {
		if (StringUtil.isBlank(desKey)) {
			try {
				desKey = DESUtils.initKey();
			} catch (Exception e) {
				LogUtil.e("SecurityConfig.getDesKey初始化deskey失败", e);
			}
		}
		return desKey;
	}

	public static String getRsaPublicKey() {
		return rsa_public_key;
	}
}
