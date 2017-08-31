using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Collections;

namespace GameLib.Util
{
    public class JSON
    {
        public static string Encode(object o){
            return JsonConvert.SerializeObject(o);
        }
        public static string Encode(object o,Formatting form)
        {
            return JsonConvert.SerializeObject(o, form);
        }
        public static object Decode(string json)
        {
            object result;
            if (string.IsNullOrEmpty(json))
            {
                result = "";
            }
            else
            {
                object obj = JsonConvert.DeserializeObject(json);
                if (obj.GetType() == typeof(string) || obj.GetType() == typeof(string))
                {
                    obj = JsonConvert.DeserializeObject(obj.ToString());
                }
                object obj2 = JSON.mydecode(obj);
                result = obj2;
            }
            return result;
        }
        private static object mydecode(object obj)
        {
            object result;
            if (obj == null)
            {
                result = null;
            }
            else
            {
                if (obj.GetType() == typeof(string))
                {
                    string text = obj.ToString();
                    if (text.Length == 19 && text[10] == 'T' && text[4] == '-' && text[13] == ':')
                    {
                        obj = Convert.ToDateTime(obj);
                    }
                }
                else
                {
                    if (obj is JObject)
                    {
                        JObject jObject = obj as JObject;
                        Hashtable hashtable = new Hashtable();
                        foreach (KeyValuePair<string, JToken> current in jObject)
                        {
                            hashtable[current.Key] = JSON.mydecode(current.Value);
                        }
                        obj = hashtable;
                    }
                    else
                    {
                        if (obj is IList)
                        {
                            ArrayList arrayList = new ArrayList();
                            arrayList.AddRange(obj as IList);
                            int i = 0;
                            int count = arrayList.Count;
                            while (i < count)
                            {
                                arrayList[i] = JSON.mydecode(arrayList[i]);
                                i++;
                            }
                            obj = arrayList;
                        }
                        else
                        {
                            if (typeof(JValue) == obj.GetType())
                            {
                                JValue jValue = (JValue)obj;
                                obj = JSON.mydecode(jValue.Value);
                            }
                        }
                    }
                }
                result = obj;
            }
            return result;
        }
        public static T Decode<T>(string str) where T:class
        {
            return JsonConvert.DeserializeObject(str, typeof(T)) as T;
        }
        public static object Decode(string str,Type type)
        {
            return JsonConvert.DeserializeObject(str,type);
        }
    }
}
