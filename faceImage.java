package ir.naji.abis.faceimage;

import ir.jame.core.compare.Loggable;
import ir.jame.core.entity.BaseEntity;
import ir.jame.core.entity.FilterProperty;
import ir.jame.core.entity.Trackable;
import ir.jame.core.file.Downloadable;
import ir.jame.portal.portalclient.security.Authorizable;
import ir.naji.abis.person.PersonEntity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyGroup;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Authorizable
@Setter
@Entity
@Table(name = "FACE_IMAGE")
public class FaceImageEntity extends BaseEntity<Long> implements Downloadable, Loggable, Trackable {

	public static final String CROPPED_IMAGE = "croppedImage";
	public static final String PERSONAL_IMAGE = "personalImage";
	public static final String FEATURE = "feature";
	public static final String IMAGE_QUALITY = "imageQuality";
	public static final String IMAGE_QUALITY_FROM = "imageQualityFrom";
	public static final String IMAGE_QUALITY_TO = "imageQualityTo";
	public static final String PERSONAL_QUALITY = "personalQuality";
	public static final String PERSONAL_QUALITY_FROM = "personalQualityFrom";
	public static final String PERSONAL_QUALITY_TO = "personalQualityTo";
	public static final String INSERT_TIME = "insertTime";
	public static final String INSERT_TIME_FROM = "insertTimeFrom";
	public static final String INSERT_TIME_TO = "insertTimeTo";
	public static final String MOVED_TO_SEARCH_SERVER = "movedToSearchServer";
	public static final String MOVED_TO_MILVUS_SEARCH_SERVER = "movedToMilvusSearchServer";
	public static final String MOVED_TO_SEARCH_SERVER_FROM = "movedToSearchServerFrom";
	public static final String MOVED_TO_SEARCH_SERVER_TO = "movedToSearchServerTo";
	public static final String IMAGE_SOURCE_SYSTEM = "imageSourceSystem";
	public static final String IMAGE_SOURCE_SYSTEM_TITLE = "imageSourceSystemTitle";
	public static final String TAKE_PICTURE_DATE = "takePictureDate";
	public static final String DUPLICATION_CHECKED = "duplicationChecked";
	public static final String PERSON = "person";
	public static final String ENGINE_TYPE = "engineType";
	public static final String CREATED_BY = "createdBy";
	public static final String CREATION_TIME = "creationTime";
	public static final String LAST_UPDATE_TIME = "lastUpdateTime";
	public static final String LAST_UPDATED_BY = "lastUpdatedBy";
	public static final String EXTRA_INFO = "extraInfo";


	private byte[] croppedImage;
	private byte[] personalImage;
	private byte[] feature;
	private Integer imageQuality;
	private Integer personalQuality;
	private Date insertTime;
	private Integer movedToSearchServer;
	private Integer movedToMilvusSearchServer;
	private String imageSourceSystem;

	private String personalImageContent;
	private String imageSourceSystemTitle;
	private Integer imageQualityFrom;
	private Integer imageQualityTo;
	private Integer personalQualityFrom;
	private Integer personalQualityTo;
	private Date insertTimeFrom;
	private Date insertTimeTo;
	private Integer movedToSearchServerFrom;
	private Integer movedToSearchServerTo;
	private Date takePictureDate;
	private Date takePictureDateFrom;
	private Date takePictureDateTo;
	private PersonEntity person;
	private String createdBy;
	private Date creationTime;
	private Date lastUpdateTime;
	private String lastUpdatedBy;
	private String extraInfo;

	private Boolean duplicationChecked = false;

	private Integer engineType;

	/* PROTECTED REGION ID(FaceImageEntityFields) ENABLED START */
//	public enum EngineType {
//		BASE(0),
//		FOREIGNERS(1),;
//
//		private Integer code;
//
//		EngineType(Integer code) {
//			this.code = code;
//		}
//
//		public Integer getCode() {
//			return code;
//		}
//	}
	public FaceImageEntity(Long id) {
		setId(id);
	}

	@Id
	@GeneratedValue(generator = "faceImageSequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "faceImageSequence", sequenceName = "FACE_IMAGE_SEQUENCE", allocationSize = 1)
	@Column(name = "ID")
	@Override
	public Long getId() {
		return super.getId();
	}

	@Column(name = "EXTRA_INFO")
	public String getExtraInfo() {
		return extraInfo;
	}

	@Lob
	@LazyGroup("lobs")
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "CROPPED_IMAGE")
	public byte[] getCroppedImage() {
		return croppedImage;
	}

	@Lob
	@LazyGroup("lobs")
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "PERSONAL_IMAGE")
	public byte[] getPersonalImage() {
		return personalImage;
	}

	@Lob
	@LazyGroup("lobs")
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "FEATURE")
	public byte[] getFeature() {
		return feature;
	}

	@Column(name = "IMAGE_QUALITY")
	public Integer getImageQuality() {
		return imageQuality;
	}

	@FilterProperty(operation = FilterProperty.GE, property = IMAGE_QUALITY)
	@Transient
	public Integer getImageQualityFrom() {
		return imageQualityFrom;
	}

	@FilterProperty(operation = FilterProperty.LE, property = IMAGE_QUALITY)
	@Transient
	public Integer getImageQualityTo() {
		return imageQualityTo;
	}

	@Column(name = "PERSONAL_QUALITY")
	public Integer getPersonalQuality() {
		return personalQuality;
	}

	@FilterProperty(operation = FilterProperty.GE, property = PERSONAL_QUALITY)
	@Transient
	public Integer getPersonalQualityFrom() {
		return personalQualityFrom;
	}

	@FilterProperty(operation = FilterProperty.LE, property = PERSONAL_QUALITY)
	@Transient
	public Integer getPersonalQualityTo() {
		return personalQualityTo;
	}

	@Column(name = "INSERT_TIME")
	public Date getInsertTime() {
		return insertTime;
	}

	@FilterProperty(operation = FilterProperty.GE, property = INSERT_TIME)
	@Transient
	public Date getInsertTimeFrom() {
		return insertTimeFrom;
	}

	@FilterProperty(operation = FilterProperty.LE, property = INSERT_TIME)
	@Transient
	public Date getInsertTimeTo() {
		return insertTimeTo;
	}

	@Column(name = "MOVED_TO_SEARCH_SERVER")
	public Integer getMovedToSearchServer() {
		return movedToSearchServer;
	}

	@Column(name = "MOVED_TO_MILVUS_SEARCH_SERVER")
	public Integer getMovedToMilvusSearchServer() {
		return movedToMilvusSearchServer;
	}

	@FilterProperty(operation = FilterProperty.GE, property = MOVED_TO_SEARCH_SERVER)
	@Transient
	public Integer getMovedToSearchServerFrom() {
		return movedToSearchServerFrom;
	}

	@FilterProperty(operation = FilterProperty.LE, property = MOVED_TO_SEARCH_SERVER)
	@Transient
	public Integer getMovedToSearchServerTo() {
		return movedToSearchServerTo;
	}

	@FilterProperty
	@Column(name = "IMAGE_SOURCE_SYSTEM")
	public String getImageSourceSystem() {
		return imageSourceSystem;
	}

	@Transient
	public String getImageSourceSystemTitle() {
		return imageSourceSystemTitle;
	}

	@Transient
	public String getPersonalImageContent() {
		return personalImageContent;
	}

	@Column(name = "TAKE_PICTURE_DATE")
	public Date getTakePictureDate() {
		return takePictureDate;
	}

	@FilterProperty(operation = FilterProperty.GE, property = TAKE_PICTURE_DATE)
	@Transient
	public Date getTakePictureDateFrom() {
		return takePictureDateFrom;
	}

	@FilterProperty(operation = FilterProperty.LE, property = TAKE_PICTURE_DATE)
	@Transient
	public Date getTakePictureDateTo() {
		return takePictureDateTo;
	}

	@Column(name = "DUPLICATION_CHECKED")
	public Boolean getDuplicationChecked() {
		return duplicationChecked != null ? duplicationChecked : false;
	}

	@FilterProperty
	@Column(name = "ENGINE_TYPE")
	public Integer getEngineType() {
		return engineType;
	}

	@FilterProperty
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_PERSON", foreignKey = @ForeignKey(name="FACE_IMAGE_PERSON_FK"))
	public PersonEntity getPerson(){
		return person;
	}

	@FilterProperty
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	@FilterProperty
	@Column(name = "CREATION_TIME", length = 6)
	public Date getCreationTime() {
		return creationTime;
	}

	@FilterProperty
	@Column(name = "LAST_UPDATE_TIME", length = 6)
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	@FilterProperty
	@Column(name = "LAST_UPDATED_BY")
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
}
